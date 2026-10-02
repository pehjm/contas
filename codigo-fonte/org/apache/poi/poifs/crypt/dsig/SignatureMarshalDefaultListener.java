/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.poifs.crypt.dsig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.SignatureMarshalListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeIterator;

public class SignatureMarshalDefaultListener
implements SignatureMarshalListener {
    private static final String OBJECT_TAG = "Object";
    private final Set<String> IGNORE_NS = new HashSet<String>(Arrays.asList(null, "http://www.w3.org/2000/xmlns/", "http://www.w3.org/2000/09/xmldsig#"));

    @Override
    public void handleElement(SignatureInfo signatureInfo, Document doc, EventTarget target, EventListener parentListener) {
        DocumentTraversal traversal = (DocumentTraversal)((Object)doc);
        Map<String, String> prefixCfg = signatureInfo.getSignatureConfig().getNamespacePrefixes();
        HashMap<String, String> prefixUsed = new HashMap<String, String>();
        NodeList nl = doc.getElementsByTagName(OBJECT_TAG);
        int objLen = nl.getLength();
        for (int i = 0; i < objLen; ++i) {
            Element objNode = (Element)nl.item(i);
            this.getAllNamespaces(traversal, objNode, prefixCfg, prefixUsed);
            prefixUsed.forEach((ns, prefix) -> objNode.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, (String)ns));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void getAllNamespaces(DocumentTraversal traversal, Element objNode, Map<String, String> prefixCfg, Map<String, String> prefixUsed) {
        prefixUsed.clear();
        NodeIterator iter = traversal.createNodeIterator(objNode, 1, null, false);
        try {
            Element node;
            while ((node = (Element)iter.nextNode()) != null) {
                this.setPrefix(node, prefixCfg, prefixUsed);
                NamedNodeMap nnm = node.getAttributes();
                int nnmLen = nnm.getLength();
                for (int j = 0; j < nnmLen; ++j) {
                    this.setPrefix(nnm.item(j), prefixCfg, prefixUsed);
                }
            }
        }
        finally {
            iter.detach();
        }
    }

    private void setPrefix(Node node, Map<String, String> prefixCfg, Map<String, String> prefixUsed) {
        String ns = node.getNamespaceURI();
        String prefix = prefixCfg.get(ns);
        if (!this.IGNORE_NS.contains(prefix)) {
            node.setPrefix(prefix);
            prefixUsed.put(ns, prefix);
        }
    }
}

