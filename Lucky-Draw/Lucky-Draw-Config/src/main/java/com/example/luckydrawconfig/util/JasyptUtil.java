package com.example.luckydrawconfig.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptUtil {

    /**
     * 加密解密使用的盐
     */
    private static String password = "123456";
    private static String prefix = "ENC(";
    private static String suffix = ")";

    private static  PooledPBEStringEncryptor encryptor;

    static {
        encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(getConfig());
    }

    public static void main(String[] args) {
        String encrypt = encrypt("6379");
        System.out.println(prefix + encrypt + suffix);
        String decrypt = decrypt(encrypt);
        System.out.println(decrypt);
    }

    /**
     * 加密方法
     * @param encrypt
     * @return
     */
    public static String encrypt(String encrypt) {
        return encryptor.encrypt(encrypt);
    }

    /**
     * 解密方法
     * @param encrypt
     * @return
     */
    public static String decrypt(String encrypt) {
        return encryptor.decrypt(encrypt);
    }

    public static SimpleStringPBEConfig getConfig(){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        // 加密解密使用的盐
        config.setPassword(password);
        // 加密算法
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }

}
