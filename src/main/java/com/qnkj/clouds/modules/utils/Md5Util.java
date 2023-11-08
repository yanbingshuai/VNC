package com.qnkj.clouds.modules.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bingshuai@nj.iscas.ac.cn
 * @since 11.01
 */

@SuppressWarnings("AlibabaUndefineMagicConstant")
public class Md5Util {
    private static final char[] HEX_CODE = "0123456789abcdef".toCharArray();

    public static String calcMd5(InputStream stream) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buf = new byte[8192];
            int len;
            while ((len = stream.read(buf)) > 0) {
                digest.update(buf, 0, len);
            }
            return toHexString(digest.digest());
        } catch (IOException e) {
            return "";
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
    public static String get(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        if (md5code.length() != 32) {
            String str = String.format("%"+(32 - md5code.length())+"d", 0).replace(" ", "0");
            md5code = str + md5code;
        }
        return md5code;
    }

    public static String toHexString(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(HEX_CODE[(b >> 4) & 0xF]);
            r.append(HEX_CODE[(b & 0xF)]);
        }
        return r.toString();
    }
}
