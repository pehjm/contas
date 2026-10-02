/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.xwpf.usermodel;

import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.CTImageData;
import javax.xml.namespace.QName;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;

public class XWPFSignatureLine
extends SignatureLine {
    static final String NS_OOXML_WP_MAIN = "http://schemas.openxmlformats.org/wordprocessingml/2006/main";
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";
    private CTSignatureLine line;

    public void parse(XWPFDocument doc) throws XmlException {
        this.line = XPathHelper.selectProperty(doc.getDocument(), CTSignatureLine.class, null, {new QName(NS_OOXML_WP_MAIN, "body")}, {new QName(NS_OOXML_WP_MAIN, "p")}, {new QName(NS_OOXML_WP_MAIN, "r")}, {new QName(NS_OOXML_WP_MAIN, "pict")}, {new QName(MS_VML_URN, "shape")}, {QNAME_SIGNATURE_LINE});
        if (this.line != null) {
            this.setSignatureShape(this.line);
            this.parse();
        }
    }

    public void add(XWPFParagraph paragraph) {
        XWPFRun r = paragraph.createRun();
        CTPicture pict = r.getCTR().addNewPict();
        this.add(pict, (image, type) -> paragraph.getDocument().addPictureData(image, XWPFSignatureLine.mapType(type)));
    }

    @Override
    protected void setRelationId(CTImageData imageData, String relId) {
        imageData.setId2(relId);
    }

    private static int mapType(PictureType type) throws InvalidFormatException {
        switch (type) {
            case BMP: {
                return 11;
            }
            case DIB: {
                return 7;
            }
            case EMF: {
                return 2;
            }
            case EPS: {
                return 10;
            }
            case GIF: {
                return 8;
            }
            case JPEG: {
                return 5;
            }
            case PICT: {
                return 4;
            }
            case PNG: {
                return 6;
            }
            case TIFF: {
                return 9;
            }
            case WMF: {
                return 3;
            }
            case WPG: {
                return 12;
            }
        }
        throw new InvalidFormatException("Unsupported picture format " + (Object)((Object)type));
    }
}

