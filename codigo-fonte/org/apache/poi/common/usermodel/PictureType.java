/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.common.usermodel;

import org.apache.poi.poifs.filesystem.FileMagic;

public enum PictureType {
    EMF("image/x-emf", ".emf"),
    WMF("image/x-wmf", ".wmf"),
    PICT("image/x-pict", ".pict"),
    JPEG("image/jpeg", ".jpg"),
    PNG("image/png", ".png"),
    DIB("image/dib", ".dib"),
    GIF("image/gif", ".gif"),
    TIFF("image/tiff", ".tif"),
    EPS("image/x-eps", ".eps"),
    BMP("image/x-ms-bmp", ".bmp"),
    WPG("image/x-wpg", ".wpg"),
    WDP("image/vnd.ms-photo", ".wdp"),
    SVG("image/svg+xml", ".svg"),
    UNKNOWN("", ".dat"),
    ERROR("", ".dat"),
    CMYKJPEG("image/jpeg", ".jpg"),
    CLIENT("", ".dat");

    public final String contentType;
    public final String extension;

    private PictureType(String contentType, String extension) {
        this.contentType = contentType;
        this.extension = extension;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getExtension() {
        return this.extension;
    }

    public static PictureType valueOf(FileMagic fm) {
        switch (fm) {
            case BMP: {
                return BMP;
            }
            case GIF: {
                return GIF;
            }
            case JPEG: {
                return JPEG;
            }
            case PNG: {
                return PNG;
            }
            case XML: {
                return SVG;
            }
            case WMF: {
                return WMF;
            }
            case EMF: {
                return EMF;
            }
            case TIFF: {
                return TIFF;
            }
        }
        return UNKNOWN;
    }
}

