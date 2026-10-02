/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.assertion;

import io.restassured.assertion.StreamVerifier;
import io.restassured.config.MatcherConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.XmlConfig;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.xml.HasXPath;
import org.w3c.dom.Element;

public class BodyMatcher {
    private static final String XPATH = "XPath";
    private Object key;
    private Matcher matcher;
    private ResponseParserRegistrar rpr;

    public Map<String, Object> validate(Response response, Object contentParser, RestAssuredConfig config) {
        boolean success = true;
        String errorMessage = "";
        contentParser = BodyMatcher.fallbackToResponseBodyIfContentParserIsNull(response, contentParser);
        if (this.key == null) {
            if (this.isXPathMatcher()) {
                Element node;
                Map<String, Object> properties;
                XmlConfig xmlConfig = config.getXmlConfig();
                boolean namespaceAware = xmlConfig.isNamespaceAware();
                Map<String, Boolean> features = xmlConfig.features();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(namespaceAware);
                if (!features.isEmpty()) {
                    features.forEach((featureName, isEnabled) -> {
                        try {
                            factory.setFeature((String)featureName, (boolean)isEnabled);
                        }
                        catch (ParserConfigurationException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                if (!(properties = xmlConfig.properties()).isEmpty()) {
                    properties.forEach(factory::setAttribute);
                }
                try {
                    node = factory.newDocumentBuilder().parse(new ByteArrayInputStream(response.asByteArray())).getDocumentElement();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (!this.matcher.matches(node)) {
                    success = false;
                    errorMessage = config.getMatcherConfig().hasErrorDescriptionType(MatcherConfig.ErrorDescriptionType.REST_ASSURED) ? String.format("Expected: %s\n  Actual: %s\n", StringUtils.trim(this.matcher.toString()), contentParser) : BodyMatcher.getDescription(this.matcher, contentParser);
                }
            } else if (!this.matcher.matches(response.asString())) {
                success = false;
                errorMessage = config.getMatcherConfig().hasErrorDescriptionType(MatcherConfig.ErrorDescriptionType.REST_ASSURED) ? "Response body doesn't match expectation.\nExpected: " + this.getMatcher() + "\n  Actual: " + contentParser + "\n" : String.format("Response body doesn't match expectation.\n%s", BodyMatcher.getDescription(this.matcher, response.asString()));
            }
        } else {
            Assertion assertion = (Assertion)StreamVerifier.newAssertion(response, this.key, this.rpr);
            Object result = null;
            if (contentParser != null) {
                if (contentParser instanceof String) {
                    boolean isEmpty = ((String)contentParser).isEmpty();
                    errorMessage = String.format("Cannot assert that path \"%s\" matches %s because the response body %s.", this.key, this.matcher, isEmpty ? "is empty" : "equal to \"$contentParser\"");
                    success = false;
                } else {
                    result = assertion.getResult(contentParser, config);
                }
            }
            if (success && !this.matcher.matches(result)) {
                success = false;
                if (config.getMatcherConfig().hasErrorDescriptionType(MatcherConfig.ErrorDescriptionType.REST_ASSURED)) {
                    if (result instanceof Object[]) {
                        result = Arrays.stream((Object[])result).map(Objects::toString).collect(Collectors.joining(","));
                    }
                    errorMessage = String.format("%s %s doesn't match.\nExpected: %s\n  Actual: %s\n", assertion.description(), this.key, BodyMatcher.removeQuotesIfString(this.matcher.toString()), BodyMatcher.removeQuotesIfString(new StringDescription().appendValue(result).toString()));
                } else {
                    errorMessage = String.format("%s %s doesn't match.\n%s", assertion.description(), this.key, BodyMatcher.getDescription(this.matcher, result));
                }
            }
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(2);
        map.put("success", success);
        map.put("errorMessage", errorMessage);
        return map;
    }

    private static String getDescription(Matcher matcher, Object actual) {
        StringDescription description = new StringDescription();
        description.appendText("\nExpected: ").appendDescriptionOf(matcher).appendText("\n  Actual: ");
        matcher.describeMismatch(actual, description);
        return ((Object)description).toString();
    }

    private static String removeQuotesIfString(String string) {
        if (StringUtils.startsWith(string, "\"") && StringUtils.endsWith(string, "\"")) {
            String start = StringUtils.removeStart(string, "\"");
            string = StringUtils.removeEnd(start, "\"");
        }
        return string;
    }

    public static Object fallbackToResponseBodyIfContentParserIsNull(Response response, Object contentParser) {
        if (contentParser == null) {
            return response.asString();
        }
        return contentParser;
    }

    private boolean isXPathMatcher() {
        Supplier<Boolean> isNestedMatcherContainingXPathMatcher = () -> {
            StringDescription description = new StringDescription();
            this.getMatcher().describeTo(description);
            return description.toString().contains(XPATH);
        };
        return this.matcher instanceof HasXPath || isNestedMatcherContainingXPathMatcher.get() != false;
    }

    public boolean requiresTextParsing() {
        return this.key == null || this.isXPathMatcher();
    }

    public boolean requiresPathParsing() {
        return !this.requiresTextParsing();
    }

    public Object getKey() {
        return this.key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Matcher getMatcher() {
        return this.matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public ResponseParserRegistrar getRpr() {
        return this.rpr;
    }

    public void setRpr(ResponseParserRegistrar rpr) {
        this.rpr = rpr;
    }
}

