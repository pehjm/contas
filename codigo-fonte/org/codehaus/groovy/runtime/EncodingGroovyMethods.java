/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.StringWriterIOException;
import groovy.lang.Writable;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.groovy.io.StringBuilderWriter;
import org.codehaus.groovy.runtime.EncodingGroovyMethodsSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class EncodingGroovyMethods {
    private static final char[] T_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static final char[] T_TABLE_URLSAFE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=".toCharArray();
    private static final String CHUNK_SEPARATOR = "\r\n";
    private static final String MD5 = "MD5";
    private static final String SHA_256 = "SHA-256";

    public static Writable encodeBase64(Byte[] data, boolean chunked) {
        return EncodingGroovyMethods.encodeBase64(DefaultTypeTransformation.convertToByteArray(data), chunked);
    }

    public static Writable encodeBase64(Byte[] data) {
        return EncodingGroovyMethods.encodeBase64(DefaultTypeTransformation.convertToByteArray(data), false);
    }

    public static Writable encodeBase64(byte[] data, boolean chunked) {
        return EncodingGroovyMethods.encodeBase64(data, chunked, false, true);
    }

    private static Writable encodeBase64(final byte[] data, final boolean chunked, final boolean urlSafe, final boolean pad) {
        return new Writable(){

            @Override
            public Writer writeTo(Writer writer) throws IOException {
                int charCount = 0;
                int dLimit = data.length / 3 * 3;
                char[] table = urlSafe ? T_TABLE_URLSAFE : T_TABLE;
                for (int dIndex = 0; dIndex != dLimit; dIndex += 3) {
                    int d = (data[dIndex] & 0xFF) << 16 | (data[dIndex + 1] & 0xFF) << 8 | data[dIndex + 2] & 0xFF;
                    writer.write(table[d >> 18]);
                    writer.write(table[d >> 12 & 0x3F]);
                    writer.write(table[d >> 6 & 0x3F]);
                    writer.write(table[d & 0x3F]);
                    if (!chunked || ++charCount != 19) continue;
                    writer.write(EncodingGroovyMethods.CHUNK_SEPARATOR);
                    charCount = 0;
                }
                if (dLimit != data.length) {
                    int d = (data[dLimit] & 0xFF) << 16;
                    if (dLimit + 1 != data.length) {
                        d |= (data[dLimit + 1] & 0xFF) << 8;
                    }
                    writer.write(table[d >> 18]);
                    writer.write(table[d >> 12 & 0x3F]);
                    if (pad) {
                        writer.write(dLimit + 1 < data.length ? table[d >> 6 & 0x3F] : 61);
                        writer.write(61);
                    } else if (dLimit + 1 < data.length) {
                        writer.write(table[d >> 6 & 0x3F]);
                    }
                    if (chunked && charCount != 0) {
                        writer.write(EncodingGroovyMethods.CHUNK_SEPARATOR);
                    }
                }
                return writer;
            }

            public String toString() {
                StringBuilderWriter buffer = new StringBuilderWriter();
                try {
                    this.writeTo(buffer);
                }
                catch (IOException e) {
                    throw new StringWriterIOException(e);
                }
                return ((Object)buffer).toString();
            }
        };
    }

    public static Writable encodeBase64(byte[] data) {
        return EncodingGroovyMethods.encodeBase64(data, false);
    }

    public static Writable encodeBase64Url(Byte[] data) {
        return EncodingGroovyMethods.encodeBase64Url(data, false);
    }

    public static Writable encodeBase64Url(Byte[] data, boolean pad) {
        return EncodingGroovyMethods.encodeBase64Url(DefaultTypeTransformation.convertToByteArray(data), pad);
    }

    public static Writable encodeBase64Url(byte[] data) {
        return EncodingGroovyMethods.encodeBase64Url(data, false);
    }

    public static Writable encodeBase64Url(byte[] data, boolean pad) {
        return EncodingGroovyMethods.encodeBase64(data, false, true, pad);
    }

    public static byte[] decodeBase64(String value) {
        return EncodingGroovyMethods.decodeBase64(value, false);
    }

    public static byte[] decodeBase64Url(String value) {
        return EncodingGroovyMethods.decodeBase64(value, true);
    }

    private static byte[] decodeBase64(String value, boolean urlSafe) {
        int byteShift = 4;
        int tmp = 0;
        boolean done = false;
        StringBuilder buffer = new StringBuilder();
        byte[] table = urlSafe ? EncodingGroovyMethodsSupport.TRANSLATE_TABLE_URLSAFE : EncodingGroovyMethodsSupport.TRANSLATE_TABLE;
        for (int i = 0; i != value.length(); ++i) {
            int sixBit;
            char c = value.charAt(i);
            int n = sixBit = c < '{' ? table[c] : 66;
            if (sixBit < 64) {
                if (done) {
                    throw new RuntimeException("= character not at end of base64 value");
                }
                tmp = tmp << 6 | sixBit;
                if (byteShift-- != 4) {
                    buffer.append((char)(tmp >> byteShift * 2 & 0xFF));
                }
            } else if (sixBit == 64) {
                --byteShift;
                done = true;
            } else if (sixBit == 66) {
                throw new RuntimeException("bad character in base64 value");
            }
            if (byteShift != 0) continue;
            byteShift = 4;
        }
        return buffer.toString().getBytes(StandardCharsets.ISO_8859_1);
    }

    public static Writable encodeHex(Byte[] data) {
        return EncodingGroovyMethods.encodeHex(DefaultTypeTransformation.convertToByteArray(data));
    }

    public static Writable encodeHex(final byte[] data) {
        return new Writable(){

            @Override
            public Writer writeTo(Writer out) throws IOException {
                for (byte datum : data) {
                    String hexString = Integer.toHexString(datum & 0xFF);
                    if (hexString.length() < 2) {
                        out.write("0");
                    }
                    out.write(hexString);
                }
                return out;
            }

            public String toString() {
                StringBuilderWriter buffer = new StringBuilderWriter();
                try {
                    this.writeTo(buffer);
                }
                catch (IOException e) {
                    throw new StringWriterIOException(e);
                }
                return ((Object)buffer).toString();
            }
        };
    }

    public static byte[] decodeHex(String value) {
        if (value.length() % 2 != 0) {
            throw new NumberFormatException("odd number of characters in hex string");
        }
        byte[] bytes = new byte[value.length() / 2];
        for (int i = 0; i < value.length(); i += 2) {
            bytes[i / 2] = (byte)Integer.parseInt(value.substring(i, i + 2), 16);
        }
        return bytes;
    }

    public static String md5(CharSequence self) throws NoSuchAlgorithmException {
        return EncodingGroovyMethods.digest(self, MD5);
    }

    public static String md5(byte[] self) throws NoSuchAlgorithmException {
        return EncodingGroovyMethods.digest(self, MD5);
    }

    public static String sha256(CharSequence self) throws NoSuchAlgorithmException {
        return EncodingGroovyMethods.digest(self, SHA_256);
    }

    public static String sha256(byte[] self) throws NoSuchAlgorithmException {
        return EncodingGroovyMethods.digest(self, SHA_256);
    }

    public static String digest(CharSequence self, String algorithm) throws NoSuchAlgorithmException {
        String text = self.toString();
        return EncodingGroovyMethods.digest(text.getBytes(StandardCharsets.UTF_8), algorithm);
    }

    public static String digest(byte[] self, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(ByteBuffer.wrap(self));
        return EncodingGroovyMethods.encodeHex(md.digest()).toString();
    }
}

