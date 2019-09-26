package com.zzr.framework.common.util.hash;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MurmurHash {
    
    private static final String UTF_8 = "UTF-8";

    public static long hash64A(byte[] data, int seed) {
        return hash64A(ByteBuffer.wrap(data), seed);
    }

    public static long hash64A(ByteBuffer buf, int seed) {
        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    public long hash(byte[] key) {
        return hash64A(key, 0x1234ABCD);
    }

    public long hash(String key) {
        return hash(encode(key));
    }

    private byte[] encode(String data) {
        try {
            return data.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        // 根据uid做分库分表
        String uid = "09cd3de4a570dc31e86234d04afbca7d";

        MurmurHash hash = new MurmurHash();
        System.out.println(hash.hash("abc"));
        System.out.println(hash.hash("abd"));
    }
}