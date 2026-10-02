/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.xml;

import groovy.lang.GroovyRuntimeException;
import groovy.xml.XmlSlurper;
import groovy.xml.XmlUtil;
import groovy.xml.slurpersupport.GPathResult;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.common.path.ObjectConverter;
import io.restassured.internal.path.xml.GroovyNodeSerializer;
import io.restassured.internal.path.xml.NodeBase;
import io.restassured.internal.path.xml.XMLAssertion;
import io.restassured.internal.path.xml.XmlPrettifier;
import io.restassured.internal.path.xml.XmlRenderer;
import io.restassured.internal.path.xml.mapping.XmlObjectDeserializer;
import io.restassured.path.xml.config.XmlParserType;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.NodeChildren;
import io.restassured.path.xml.exception.XmlPathException;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.Validate;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.InputSource;

public class XmlPath {
    public static XmlPathConfig config = null;
    private final CompatibilityMode mode;
    private LazyXmlParser lazyXmlParser;
    private XmlPathConfig xmlPathConfig = null;
    private Map<String, Object> params;
    private String rootPath = "";

    public XmlPath(String text) {
        this(CompatibilityMode.XML, text);
    }

    public XmlPath(InputStream stream) {
        this(CompatibilityMode.XML, stream);
    }

    public XmlPath(InputSource source) {
        this(CompatibilityMode.XML, source);
    }

    public XmlPath(File file) {
        this(CompatibilityMode.XML, file);
    }

    public XmlPath(Reader reader) {
        this(CompatibilityMode.XML, reader);
    }

    public XmlPath(URI uri) {
        this(CompatibilityMode.XML, uri);
    }

    public XmlPath(CompatibilityMode mode, String text) {
        Validate.notNull(mode, "Compatibility mode cannot be null", new Object[0]);
        this.mode = mode;
        this.lazyXmlParser = this.parseText(text);
    }

    public XmlPath(CompatibilityMode mode, InputStream stream) {
        Validate.notNull(mode, "Compatibility mode cannot be null", new Object[0]);
        this.mode = mode;
        this.lazyXmlParser = this.parseInputStream(stream);
    }

    public XmlPath(CompatibilityMode mode, InputSource source) {
        Validate.notNull(mode, "Compatibility mode cannot be null", new Object[0]);
        this.mode = mode;
        this.lazyXmlParser = this.parseInputSource(source);
    }

    public XmlPath(CompatibilityMode mode, File file) {
        Validate.notNull(mode, "Compatibility mode cannot be null", new Object[0]);
        this.mode = mode;
        this.lazyXmlParser = this.parseFile(file);
    }

    public XmlPath(CompatibilityMode mode, Reader reader) {
        Validate.notNull(mode, "Compatibility mode cannot be null", new Object[0]);
        this.mode = mode;
        this.lazyXmlParser = this.parseReader(reader);
    }

    public XmlPath(CompatibilityMode mode, URI uri) {
        Validate.notNull(mode, "Compatibility mode cannot be null", new Object[0]);
        this.mode = mode;
        this.lazyXmlParser = this.parseURI(uri);
    }

    public XmlPath using(JAXBObjectMapperFactory factory) {
        return new XmlPath(this, this.getXmlPathConfig().jaxbObjectMapperFactory(factory));
    }

    public XmlPath using(XmlPathConfig config) {
        return new XmlPath(this, config);
    }

    private XmlPath(XmlPath xmlPath, XmlPathConfig config) {
        this.xmlPathConfig = config;
        this.mode = xmlPath.mode;
        this.lazyXmlParser = xmlPath.lazyXmlParser.changeCompatibilityMode(this.mode).changeConfig(config);
        if (xmlPath.params != null) {
            this.params = new HashMap<String, Object>(xmlPath.params);
        }
    }

    public Node get() {
        return (Node)this.get("$");
    }

    public <T> T get(String path) {
        AssertParameter.notNull(path, "path");
        return this.getFromPath(path, true);
    }

    public <T> List<T> getList(String path) {
        return this.getAsList(path);
    }

    public <T> List<T> getList(String path, Class<T> genericType) {
        return this.getAsList(path, genericType);
    }

    public <K, V> Map<K, V> getMap(String path) {
        return (Map)this.get(path);
    }

    public <K, V> Map<K, V> getMap(String path, Class<K> keyType, Class<V> valueType) {
        Map originalMap = (Map)this.get(path);
        HashMap<Object, Object> newMap = new HashMap<Object, Object>();
        for (Map.Entry entry : originalMap.entrySet()) {
            Object key = entry.getKey() == null ? null : (Object)this.convertObjectTo(entry.getKey(), keyType);
            Object value = entry.getValue() == null ? null : (Object)this.convertObjectTo(entry.getValue(), valueType);
            newMap.put(key, value);
        }
        return Collections.unmodifiableMap(newMap);
    }

    public <T> T getObject(String path, Class<T> objectType) {
        T object = this.getFromPath(path, false);
        return this.getObjectAsType(object, objectType);
    }

    private <T> T getObjectAsType(Object object, Class<T> objectType) {
        if (object == null) {
            return null;
        }
        if (object instanceof GPathResult) {
            try {
                object = XmlUtil.serialize((GPathResult)object);
            }
            catch (GroovyRuntimeException e) {
                throw new IllegalArgumentException("Failed to convert XML to Java Object. If you're trying convert to a list then use the getList method instead.", e);
            }
        } else if (object instanceof groovy.xml.slurpersupport.Node) {
            object = GroovyNodeSerializer.toXML((groovy.xml.slurpersupport.Node)object);
        }
        XmlPathConfig cfg = new XmlPathConfig(this.getXmlPathConfig());
        if (cfg.hasCustomJaxbObjectMapperFactory()) {
            cfg = cfg.defaultParserType(XmlParserType.JAXB);
        }
        if (!(object instanceof String)) {
            throw new IllegalStateException("Internal error: XML object was not an instance of String, please report to the REST Assured mailing-list.");
        }
        return XmlObjectDeserializer.deserialize((String)object, objectType, cfg);
    }

    private <T> T getFromPath(String path, boolean convertToJavaObject) {
        GPathResult input = this.lazyXmlParser.invoke();
        XMLAssertion xmlAssertion = new XMLAssertion();
        if (this.params != null) {
            xmlAssertion.setParams(this.params);
        }
        String root = this.rootPath.equals("") ? this.rootPath : (this.rootPath.endsWith(".") ? this.rootPath : this.rootPath + ".");
        xmlAssertion.setKey(root + path);
        return (T)xmlAssertion.getResult(input, convertToJavaObject, true);
    }

    public int getInt(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Integer.class);
    }

    public boolean getBoolean(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Boolean.class);
    }

    public Node getNode(String path) {
        return this.convertObjectTo(this.get(path), Node.class);
    }

    public NodeChildren getNodeChildren(String path) {
        return this.convertObjectTo(this.get(path), NodeChildren.class);
    }

    public char getChar(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Character.class).charValue();
    }

    public byte getByte(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Byte.class);
    }

    public short getShort(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Short.class);
    }

    public float getFloat(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Float.class).floatValue();
    }

    public double getDouble(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Double.class);
    }

    public long getLong(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, Long.class);
    }

    public String getString(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, String.class);
    }

    public UUID getUUID(String path) {
        Object object = this.get(path);
        return this.convertObjectTo(object, UUID.class);
    }

    public XmlPath param(String key, Object value) {
        XmlPath newP = new XmlPath(this, this.getXmlPathConfig());
        if (newP.params == null) {
            newP.params = new HashMap<String, Object>();
        }
        newP.params.put(key, value);
        return newP;
    }

    public XmlPath peek() {
        GPathResult result = this.lazyXmlParser.invoke();
        String render = XmlRenderer.render(result);
        System.out.println(render);
        return this;
    }

    public XmlPath prettyPeek() {
        GPathResult result = this.lazyXmlParser.invoke();
        String prettify = XmlPrettifier.prettify(result);
        System.out.println(prettify);
        return this;
    }

    public String prettify() {
        return XmlPrettifier.prettify(this.lazyXmlParser.invoke());
    }

    public String prettyPrint() {
        String pretty = this.prettify();
        System.out.println(pretty);
        return pretty;
    }

    public static XmlPath given(String text) {
        return new XmlPath(text);
    }

    public static XmlPath given(InputStream stream) {
        return new XmlPath(stream);
    }

    public static XmlPath given(InputSource source) {
        return new XmlPath(source);
    }

    public static XmlPath given(File file) {
        return new XmlPath(file);
    }

    public static XmlPath given(Reader reader) {
        return new XmlPath(reader);
    }

    public static XmlPath given(URI uri) {
        return new XmlPath(uri);
    }

    public static XmlPath with(InputStream stream) {
        return new XmlPath(stream);
    }

    public static XmlPath with(String text) {
        return new XmlPath(text);
    }

    public static XmlPath with(InputSource source) {
        return new XmlPath(source);
    }

    public static XmlPath with(File file) {
        return new XmlPath(file);
    }

    public static XmlPath with(Reader reader) {
        return new XmlPath(reader);
    }

    public static XmlPath with(URI uri) {
        return new XmlPath(uri);
    }

    public static XmlPath from(InputStream stream) {
        return new XmlPath(stream);
    }

    public static XmlPath from(String text) {
        return new XmlPath(text);
    }

    public static XmlPath from(InputSource source) {
        return new XmlPath(source);
    }

    public static XmlPath from(File file) {
        return new XmlPath(file);
    }

    public static XmlPath from(Reader reader) {
        return new XmlPath(reader);
    }

    public static XmlPath from(URI uri) {
        return new XmlPath(uri);
    }

    private LazyXmlParser parseText(final String text) {
        return new LazyXmlParser(this.getXmlPathConfig(), this.mode){

            @Override
            protected GPathResult method(XmlSlurper slurper) throws Exception {
                return slurper.parseText(text);
            }
        };
    }

    @Deprecated
    public XmlPath setRoot(String rootPath) {
        return this.setRootPath(rootPath);
    }

    public XmlPath setRootPath(String rootPath) {
        AssertParameter.notNull(rootPath, "Root path");
        this.rootPath = rootPath;
        return this;
    }

    private <T> List<T> getAsList(String path) {
        return this.getAsList(path, null);
    }

    private <T> List<T> getAsList(String path, Class<?> explicitType) {
        Object returnObject = this.get(path);
        if (returnObject instanceof NodeChildren) {
            NodeChildren nodeChildren = (NodeChildren)returnObject;
            returnObject = this.convertElementsListTo(nodeChildren.list(), explicitType);
        } else if (!(returnObject instanceof List)) {
            ArrayList<String> asList = new ArrayList<String>();
            if (returnObject != null) {
                String e = explicitType == null ? returnObject.toString() : this.convertObjectTo(returnObject, explicitType);
                asList.add(e);
            }
            returnObject = asList;
        } else if (explicitType != null) {
            List returnObjectAsList = (List)returnObject;
            ArrayList convertedList = new ArrayList();
            for (Object o : returnObjectAsList) {
                convertedList.add(this.convertObjectTo(o, explicitType));
            }
            returnObject = convertedList;
        }
        return returnObject == null ? null : Collections.unmodifiableList((List)returnObject);
    }

    private List<Object> convertElementsListTo(List<Node> list, Class<?> explicitType) {
        ArrayList<Object> convertedList = new ArrayList<Object>();
        if (list != null && list.size() > 0) {
            for (Node node : list) {
                if (explicitType == null) {
                    convertedList.add(node.toString());
                    continue;
                }
                convertedList.add(this.convertObjectTo(node, explicitType));
            }
        }
        return convertedList;
    }

    private <T> T convertObjectTo(Object object, Class<T> explicitType) {
        if (object instanceof NodeBase && !ObjectConverter.canConvert(object, explicitType)) {
            return this.getObjectAsType(((NodeBase)object).getBackingGroovyObject(), explicitType);
        }
        return ObjectConverter.convertObjectTo(object, explicitType);
    }

    private LazyXmlParser parseInputStream(final InputStream stream) {
        return new LazyXmlParser(this.getXmlPathConfig(), this.mode){

            @Override
            protected GPathResult method(XmlSlurper slurper) throws Exception {
                return slurper.parse(stream);
            }
        };
    }

    private LazyXmlParser parseReader(final Reader reader) {
        return new LazyXmlParser(this.getXmlPathConfig(), this.mode){

            @Override
            protected GPathResult method(XmlSlurper slurper) throws Exception {
                return slurper.parse(reader);
            }
        };
    }

    private LazyXmlParser parseFile(final File file) {
        return new LazyXmlParser(this.getXmlPathConfig(), this.mode){

            @Override
            protected GPathResult method(XmlSlurper slurper) throws Exception {
                return slurper.parse(file);
            }
        };
    }

    private LazyXmlParser parseURI(final URI uri) {
        return new LazyXmlParser(this.getXmlPathConfig(), this.mode){

            @Override
            protected GPathResult method(XmlSlurper slurper) throws Exception {
                return slurper.parse(uri.toString());
            }
        };
    }

    private LazyXmlParser parseInputSource(final InputSource source) {
        return new LazyXmlParser(this.getXmlPathConfig(), this.mode){

            @Override
            protected GPathResult method(XmlSlurper slurper) throws Exception {
                return slurper.parse(source);
            }
        };
    }

    private XmlPathConfig getXmlPathConfig() {
        XmlPathConfig cfg = config == null && this.xmlPathConfig == null ? new XmlPathConfig() : (this.xmlPathConfig != null ? this.xmlPathConfig : config);
        return cfg;
    }

    public static void reset() {
        config = null;
    }

    public static enum CompatibilityMode {
        XML,
        HTML;

    }

    private static abstract class LazyXmlParser {
        private volatile XmlPathConfig config;
        private volatile CompatibilityMode compatibilityMode;
        private GPathResult input;
        private boolean isInputParsed;

        protected abstract GPathResult method(XmlSlurper var1) throws Exception;

        protected LazyXmlParser(XmlPathConfig config, CompatibilityMode compatibilityMode) {
            this.config = config;
            this.compatibilityMode = compatibilityMode;
        }

        public LazyXmlParser changeCompatibilityMode(CompatibilityMode mode) {
            this.compatibilityMode = mode;
            return this;
        }

        public LazyXmlParser changeConfig(XmlPathConfig config) {
            this.config = config;
            return this;
        }

        public synchronized GPathResult invoke() {
            if (this.isInputParsed) {
                return this.input;
            }
            this.isInputParsed = true;
            try {
                XmlSlurper slurper;
                if (this.compatibilityMode == CompatibilityMode.XML) {
                    slurper = new XmlSlurper(this.config.isValidating(), this.config.isNamespaceAware(), this.config.isAllowDocTypeDeclaration());
                } else {
                    Parser p = new Parser();
                    slurper = new XmlSlurper(p);
                }
                Map<String, Boolean> features = this.config.features();
                for (Map.Entry<String, Boolean> entry : features.entrySet()) {
                    slurper.setFeature(entry.getKey(), entry.getValue());
                }
                Map<String, Object> properties = this.config.properties();
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    slurper.setProperty(entry.getKey(), entry.getValue());
                }
                GPathResult gPathResult = this.method(slurper);
                if (this.config.hasDeclaredNamespaces()) {
                    gPathResult.declareNamespace(this.config.declaredNamespaces());
                }
                this.input = gPathResult;
                return this.input;
            }
            catch (Exception e) {
                throw new XmlPathException("Failed to parse the XML document", e);
            }
        }
    }
}

