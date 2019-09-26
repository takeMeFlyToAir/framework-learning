package com.zzr.framework.common.util.hash;

import java.util.Random;
import java.util.zip.CRC32;

/**
 * Created by zhaozhirong on 2019/8/23.
 */
public class CommonHash {

    private static Random random = new Random();


    public static long hash(String value) {
        if (value == null || value.trim().isEmpty() || value.trim().equalsIgnoreCase("null")) {
            return random.nextInt(100);
        }

        CRC32 crc32 = new CRC32();
        crc32.update(value.getBytes());
        return crc32.getValue();
    }
}
