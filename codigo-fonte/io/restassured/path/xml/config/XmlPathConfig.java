/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.xml.config;

import io.restassured.common.mapper.resolver.ObjectMapperResolver;
import io.restassured.path.xml.config.XmlParserType;
import io.restassured.path.xml.mapper.factory.DefaultJAXBObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import io.restassured.path.xml.mapping.XmlPathObjectDeserializer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class XmlPathConfig {
    private static final boolean DEFAULT_VALIDATING = false;
    private static final boolean DEFAULT_NAMESPACE_AWARE = true;
    private static final boolean DEFAULT_ALLOW_DOC_TYPE_DECLARATION = false;
    private final XmlPathObjectDeserializer defaultDeserializer;
    private final JAXBObjectMapperFactory jaxbObjectMapperFactory;
    private final XmlParserType defaultParserType;
    private final String charset;
    private final Map<String, Boolean> features;
    private final Map<String, Object> properties;
    private final Map<String, String> declaredNamespaces;
    private final boolean validating;
    private final boolean namespaceAware;
    private final boolean allowDocTypeDeclaration;

    public XmlPathConfig(XmlPathConfig config) {
        this(config.jaxbObjectMapperFactory(), config.defaultParserType(), config.defaultDeserializer(), config.charset(), new HashMap<String, Boolean>(), new HashMap<String, String>(), new HashMap<String, Object>(), config.isValidating(), config.isNamespaceAware(), config.isAllowDocTypeDeclaration());
    }

    public XmlPathConfig() {
        this(XmlPathConfig.newDefaultJaxbObjectMapperFactoryOrNull(), null, null, XmlPathConfig.defaultCharset(), new HashMap<String, Boolean>(), new HashMap<String, String>(), new HashMap<String, Object>(), false, true, false);
    }

    public XmlPathConfig(String defaultCharset) {
        this(XmlPathConfig.newDefaultJaxbObjectMapperFactoryOrNull(), null, null, defaultCharset, new HashMap<String, Boolean>(), new HashMap<String, String>(), new HashMap<String, Object>(), false, true, false);
    }

    private XmlPathConfig(JAXBObjectMapperFactory jaxbObjectMapperFactory, XmlParserType defaultParserType, XmlPathObjectDeserializer defaultDeserializer, String charset, Map<String, Boolean> features, Map<String, String> declaredNamespaces, Map<String, Object> properties, boolean validating, boolean namespaceAware, boolean allowDocTypeDeclaration) {
        charset = StringUtils.trimToNull(charset);
        if (charset == null) {
            throw new IllegalArgumentException("Charset cannot be empty");
        }
        this.charset = charset;
        this.defaultDeserializer = defaultDeserializer;
        this.defaultParserType = defaultParserType;
        this.jaxbObjectMapperFactory = jaxbObjectMapperFactory;
        this.features = features;
        this.declaredNamespaces = declaredNamespaces;
        this.properties = properties;
        this.validating = validating;
        this.namespaceAware = namespaceAware;
        this.allowDocTypeDeclaration = allowDocTypeDeclaration;
    }

    private static String defaultCharset() {
        return Charset.defaultCharset().name();
    }

    public Map<String, Boolean> features() {
        return new HashMap<String, Boolean>(this.features);
    }

    public XmlPathConfig features(Map<String, Boolean> features) {
        Validate.notNull(features, "Features cannot be null", new Object[0]);
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig feature(String uri, boolean enabled) {
        Validate.notEmpty(uri, "URI cannot be empty", new Object[0]);
        HashMap<String, Boolean> newFeatures = new HashMap<String, Boolean>(this.features);
        newFeatures.put(uri, enabled);
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, newFeatures, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig validating(boolean isValidating) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, this.properties, isValidating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public boolean isValidating() {
        return this.validating;
    }

    public XmlPathConfig namespaceAware(boolean namespaceAware) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, this.properties, this.validating, namespaceAware, this.allowDocTypeDeclaration);
    }

    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }

    public XmlPathConfig allowDocTypeDeclaration(boolean allowDocTypeDeclaration) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, allowDocTypeDeclaration);
    }

    public boolean isAllowDocTypeDeclaration() {
        return this.allowDocTypeDeclaration;
    }

    public Map<String, Object> properties() {
        return new HashMap<String, Object>(this.properties);
    }

    public XmlPathConfig properties(Map<String, Object> properties) {
        Validate.notNull(properties, "Properties cannot be null", new Object[0]);
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig property(String name, Object value) {
        Validate.notEmpty(name, "Name cannot be empty", new Object[0]);
        HashMap<String, Object> newProperties = new HashMap<String, Object>(this.properties);
        newProperties.put(name, value);
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, newProperties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig disableLoadingOfExternalDtd() {
        HashMap<String, Boolean> newFeatures = new HashMap<String, Boolean>(this.features);
        newFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        newFeatures.put("http://apache.org/xml/features/disallow-doctype-decl", false);
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, newFeatures, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public String charset() {
        return this.charset;
    }

    public XmlPathConfig charset(String charset) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, charset, this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlParserType defaultParserType() {
        return this.defaultParserType;
    }

    public boolean hasDefaultParserType() {
        return this.defaultParserType != null;
    }

    public boolean hasCustomJaxbObjectMapperFactory() {
        return this.jaxbObjectMapperFactory() != null && this.jaxbObjectMapperFactory().getClass() != DefaultJAXBObjectMapperFactory.class;
    }

    public XmlPathObjectDeserializer defaultDeserializer() {
        return this.defaultDeserializer;
    }

    public boolean hasDefaultDeserializer() {
        return this.defaultDeserializer != null;
    }

    public XmlPathConfig defaultParserType(XmlParserType defaultParserType) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig defaultObjectDeserializer(XmlPathObjectDeserializer defaultObjectDeserializer) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, defaultObjectDeserializer, this.charset, this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public JAXBObjectMapperFactory jaxbObjectMapperFactory() {
        return this.jaxbObjectMapperFactory;
    }

    public Map<String, String> declaredNamespaces() {
        return new HashMap<String, String>(this.declaredNamespaces);
    }

    public XmlPathConfig declareNamespaces(Map<String, String> namespacesToDeclare) {
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, namespacesToDeclare, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig declaredNamespace(String prefix, String namespaceURI) {
        Validate.notEmpty(prefix, "Prefix cannot be empty", new Object[0]);
        Validate.notEmpty(namespaceURI, "Namespace URI cannot be empty", new Object[0]);
        HashMap<String, String> updatedNamespaces = new HashMap<String, String>(this.declaredNamespaces);
        updatedNamespaces.put(prefix, namespaceURI);
        return new XmlPathConfig(this.jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, updatedNamespaces, this.properties, this.validating, true, this.allowDocTypeDeclaration);
    }

    public XmlPathConfig jaxbObjectMapperFactory(JAXBObjectMapperFactory jaxbObjectMapperFactory) {
        return new XmlPathConfig(jaxbObjectMapperFactory, this.defaultParserType, this.defaultDeserializer, this.charset, this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration);
    }

    public boolean hasDeclaredNamespaces() {
        return !this.declaredNamespaces.isEmpty();
    }

    public XmlPathConfig with() {
        return this;
    }

    public XmlPathConfig and() {
        return this;
    }

    public static XmlPathConfig xmlPathConfig() {
        return new XmlPathConfig();
    }

    private static DefaultJAXBObjectMapperFactory newDefaultJaxbObjectMapperFactoryOrNull() {
        if (ObjectMapperResolver.isJAXBInClassPath()) {
            return new DefaultJAXBObjectMapperFactory();
        }
        return null;
    }
}

