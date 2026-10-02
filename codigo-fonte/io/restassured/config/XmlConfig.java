/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

public class XmlConfig
implements Config {
    private static final boolean DEFAULT_VALIDATING = false;
    private static final boolean DEFAULT_NAMESPACE_AWARE = true;
    private static final boolean DEFAULT_ALLOW_DOC_TYPE_DECLARATION = false;
    private final Map<String, Object> properties;
    private final Map<String, Boolean> features;
    private final Map<String, String> declaredNamespaces;
    private final boolean validating;
    private final boolean namespaceAware;
    private final boolean allowDocTypeDeclaration;
    private final boolean isUserConfigured;

    public XmlConfig() {
        this(new HashMap<String, Boolean>(), new HashMap<String, String>(), new HashMap<String, Object>(), false, true, false, false);
    }

    private XmlConfig(Map<String, Boolean> features, Map<String, String> declaredNamespaces, Map<String, Object> properties, boolean validating, boolean namespaceAware, boolean allowDocTypeDeclaration, boolean isUserConfigured) {
        Validate.notNull(features, "Features cannot be null", new Object[0]);
        Validate.notNull(declaredNamespaces, "Declared namespaces cannot be null", new Object[0]);
        Validate.notNull(properties, "Properties cannot be null", new Object[0]);
        this.validating = validating;
        this.namespaceAware = namespaceAware;
        this.allowDocTypeDeclaration = allowDocTypeDeclaration;
        this.features = features;
        this.declaredNamespaces = declaredNamespaces;
        this.properties = properties;
        this.isUserConfigured = isUserConfigured;
    }

    public Map<String, Boolean> features() {
        return new HashMap<String, Boolean>(this.features);
    }

    public Map<String, Object> properties() {
        return new HashMap<String, Object>(this.properties);
    }

    public XmlConfig features(Map<String, Boolean> features) {
        return new XmlConfig(features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration, true);
    }

    public XmlConfig properties(Map<String, Object> properties) {
        return new XmlConfig(this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration, true);
    }

    public XmlConfig feature(String uri, boolean enabled) {
        Validate.notEmpty(uri, "URI cannot be empty", new Object[0]);
        HashMap<String, Boolean> newFeatures = new HashMap<String, Boolean>(this.features);
        newFeatures.put(uri, enabled);
        return new XmlConfig(newFeatures, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration, true);
    }

    public XmlConfig property(String name, Object value) {
        Validate.notEmpty(name, "Name cannot be empty", new Object[0]);
        HashMap<String, Object> newProperties = new HashMap<String, Object>(this.properties);
        newProperties.put(name, value);
        return new XmlConfig(this.features, this.declaredNamespaces, newProperties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration, true);
    }

    public Map<String, String> declaredNamespaces() {
        return new HashMap<String, String>(this.declaredNamespaces);
    }

    public XmlConfig declareNamespaces(Map<String, String> namespacesToDeclare) {
        boolean shouldBeNamespaceAware = namespacesToDeclare == null ? this.namespaceAware : !namespacesToDeclare.isEmpty();
        return new XmlConfig(this.features, namespacesToDeclare, this.properties, this.validating, shouldBeNamespaceAware, this.allowDocTypeDeclaration, true);
    }

    public XmlConfig declareNamespace(String prefix, String namespaceURI) {
        Validate.notEmpty(prefix, "Prefix cannot be empty", new Object[0]);
        Validate.notEmpty(namespaceURI, "Namespace URI cannot be empty", new Object[0]);
        HashMap<String, String> updatedNamespaces = new HashMap<String, String>(this.declaredNamespaces);
        updatedNamespaces.put(prefix, namespaceURI);
        return new XmlConfig(this.features, updatedNamespaces, this.properties, this.validating, true, this.allowDocTypeDeclaration, true);
    }

    public XmlConfig disableLoadingOfExternalDtd() {
        HashMap<String, Boolean> newFeatures = new HashMap<String, Boolean>(this.features);
        newFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        return new XmlConfig(newFeatures, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, this.allowDocTypeDeclaration, true);
    }

    public XmlConfig validating(boolean isValidating) {
        return new XmlConfig(this.features, this.declaredNamespaces, this.properties, isValidating, this.namespaceAware, this.allowDocTypeDeclaration, true);
    }

    public boolean isValidating() {
        return this.validating;
    }

    public XmlConfig namespaceAware(boolean shouldBeAwareOfNamespaces) {
        return new XmlConfig(this.features, this.declaredNamespaces, this.properties, this.validating, shouldBeAwareOfNamespaces, this.allowDocTypeDeclaration, true);
    }

    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }

    public XmlConfig allowDocTypeDeclaration(boolean allowDocTypeDeclaration) {
        return new XmlConfig(this.features, this.declaredNamespaces, this.properties, this.validating, this.namespaceAware, allowDocTypeDeclaration, true);
    }

    public boolean isAllowDocTypeDeclaration() {
        return this.allowDocTypeDeclaration;
    }

    public XmlConfig with() {
        return this;
    }

    public XmlConfig and() {
        return this;
    }

    public static XmlConfig xmlConfig() {
        return new XmlConfig();
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }
}

