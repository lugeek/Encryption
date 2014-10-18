package com.lugeek.algorithm;

import java.security.MessageDigest;

public class sha {
    /**
     *
     * @param data to be encrypted
     * @param shaN encrypt method,SHA-1,SHA-224,SHA-256,SHA-384,SHA-512
     * @return 已加密的数据
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data, String shaN) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(shaN);
        sha.update(data);
        return sha.digest();

    }
}
