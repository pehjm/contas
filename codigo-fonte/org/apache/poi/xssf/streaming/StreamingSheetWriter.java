/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.xssf.streaming;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.streaming.SheetDataWriter;

public class StreamingSheetWriter
extends SheetDataWriter {
    private static final Logger LOG = LogManager.getLogger(StreamingSheetWriter.class);

    public StreamingSheetWriter() throws IOException {
        throw new RuntimeException("StreamingSheetWriter requires OutputStream");
    }

    public StreamingSheetWriter(OutputStream out) throws IOException {
        super(StreamingSheetWriter.createWriter(out));
        LOG.atDebug().log("Preparing SXSSF sheet writer");
    }

    @Override
    public File createTempFile() throws IOException {
        throw new RuntimeException("Not supported with StreamingSheetWriter");
    }

    @Override
    public Writer createWriter(File fd) throws IOException {
        throw new RuntimeException("Not supported with StreamingSheetWriter");
    }

    protected static Writer createWriter(OutputStream out) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
    }

    @Override
    public void close() throws IOException {
        this._out.flush();
    }

    @Override
    public InputStream getWorksheetXMLInputStream() throws IOException {
        throw new RuntimeException("Not supported with StreamingSheetWriter");
    }

    @Override
    boolean dispose() throws IOException {
        this._out.close();
        return true;
    }
}

