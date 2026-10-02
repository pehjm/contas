/*
 * Decompiled with CFR 0.152.
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import javax.xml.catalog.Catalog;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import org.xml.sax.EntityResolver;

public class MavenPluginResolver {
    public static EntityResolver getResolver(String catalogLocation) {
        if (catalogLocation == null) {
            return null;
        }
        CatalogFeatures features = CatalogFeatures.builder().with(CatalogFeatures.Feature.PREFER, "system").build();
        Catalog catalog = CatalogManager.catalog(features, new File(catalogLocation).toURI());
        return CatalogManager.catalogResolver(catalog);
    }
}

