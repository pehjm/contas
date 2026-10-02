/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.log;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class UrlDecoder {
    private UrlDecoder() {
    }

    public static String urlDecode(String content, Charset charset, boolean plusAsBlank) {
        if (content == null) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.allocate(content.length());
        CharBuffer cb = CharBuffer.wrap(content);
        while (cb.hasRemaining()) {
            char c = cb.get();
            if (c == '%' && cb.remaining() >= 2) {
                char uc = cb.get();
                char lc = cb.get();
                int u = Character.digit(uc, 16);
                int l = Character.digit(lc, 16);
                if (u != -1 && l != -1) {
                    bb.put((byte)((u << 4) + l));
                    continue;
                }
                bb.put((byte)37);
                bb.put((byte)uc);
                bb.put((byte)lc);
                continue;
            }
            if (plusAsBlank && c == '+') {
                bb.put((byte)32);
                continue;
            }
            bb.put((byte)c);
        }
        bb.flip();
        return charset.decode(bb).toString();
    }
}

