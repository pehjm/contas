/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.sl.usermodel.MetroShapeProvider;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;

@Internal
public class XSLFMetroShape
implements MetroShapeProvider {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public XSLFShape parseShape(byte[] metroBytes) throws IOException {
        try (OPCPackage pkg = OPCPackage.open(new UnsynchronizedByteArrayInputStream(metroBytes));){
            PackagePartName shapePN = PackagingURIHelper.createPartName("/drs/shapexml.xml");
            PackagePart shapePart = pkg.getPart(shapePN);
            if (shapePart == null) {
                XSLFShape xSLFShape = null;
                return xSLFShape;
            }
            CTGroupShape gs = (CTGroupShape)CTGroupShape.Factory.parse(shapePart.getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            XSLFGroupShape xgs = new XSLFGroupShape(gs, null);
            XSLFShape xSLFShape = xgs.getShapes().get(0);
            return xSLFShape;
        }
        catch (InvalidFormatException | XmlException e) {
            throw new IOException("can't parse metro shape", e);
        }
    }
}

