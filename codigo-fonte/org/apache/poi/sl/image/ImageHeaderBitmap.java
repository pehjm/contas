/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.sl.image;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;

@Internal
public class ImageHeaderBitmap {
    private static final Logger LOG = LogManager.getLogger(ImageHeaderBitmap.class);
    private final Dimension size;

    public ImageHeaderBitmap(byte[] data, int offset) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new UnsynchronizedByteArrayInputStream(data, offset, data.length - offset));
        }
        catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("Can't determine image dimensions");
        }
        this.size = img == null ? new Dimension(200, 200) : new Dimension((int)Units.pixelToPoints(img.getWidth()), (int)Units.pixelToPoints(img.getHeight()));
    }

    public Dimension getSize() {
        return this.size;
    }
}

