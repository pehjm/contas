/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.openxml4j.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.TempFile;

class ZipArchiveFakeEntry
extends ZipArchiveEntry
implements Closeable {
    private static Logger LOG = LogManager.getLogger(ZipArchiveFakeEntry.class);
    private byte[] data;
    private File tempFile;
    private EncryptedTempData encryptedTempData;

    ZipArchiveFakeEntry(ZipArchiveEntry entry, InputStream inp) throws IOException {
        super(entry.getName());
        long entrySize = entry.getSize();
        int threshold = ZipInputStreamZipEntrySource.getThresholdBytesForTempFiles();
        if (threshold >= 0 && entrySize >= (long)threshold) {
            if (ZipInputStreamZipEntrySource.shouldEncryptTempFiles()) {
                this.encryptedTempData = new EncryptedTempData();
                try (OutputStream os = this.encryptedTempData.getOutputStream();){
                    IOUtils.copy(inp, os);
                }
            } else {
                this.tempFile = TempFile.createTempFile("poi-zip-entry", ".tmp");
                LOG.atInfo().log("created for temp file {} for zip entry {} of size {} bytes", () -> this.tempFile.getAbsolutePath(), () -> entry.getName(), () -> entrySize);
                IOUtils.copy(inp, this.tempFile);
            }
        } else {
            if (entrySize < -1L || entrySize >= Integer.MAX_VALUE) {
                throw new IOException("ZIP entry size is too large or invalid");
            }
            this.data = entrySize == -1L ? IOUtils.toByteArray(inp) : IOUtils.toByteArray(inp, (int)entrySize);
        }
    }

    public InputStream getInputStream() {
        if (this.encryptedTempData != null) {
            try {
                return this.encryptedTempData.getInputStream();
            }
            catch (IOException e) {
                throw new RuntimeException("failed to read from encryped temp data", e);
            }
        }
        if (this.tempFile != null) {
            try {
                return new FileInputStream(this.tempFile);
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException("temp file " + this.tempFile.getAbsolutePath() + " is missing");
            }
        }
        return new UnsynchronizedByteArrayInputStream(this.data);
    }

    @Override
    public void close() throws IOException {
        this.data = null;
        if (this.encryptedTempData != null) {
            this.encryptedTempData.dispose();
        }
        if (this.tempFile != null && this.tempFile.exists() && !this.tempFile.delete()) {
            LOG.atDebug().log("temp file was already deleted (probably due to previous call to close this resource)");
        }
    }
}

