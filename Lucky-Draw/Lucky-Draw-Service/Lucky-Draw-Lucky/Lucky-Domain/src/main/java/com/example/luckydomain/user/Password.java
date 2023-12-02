package com.example.luckydomain.user;

import cn.hutool.crypto.digest.MD5;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Password {
    private EncryptionPassWord encryptionPassWord;

    public Password(EncryptionPassWord encryptionPassWord) {
        this.encryptionPassWord = encryptionPassWord;
    }

    public static String getEncryptionPassWord(String password) {
        return MD5.create().digestHex(password);
    }

    @Getter
    public static class EncryptionPassWord {

        private String password;

        public EncryptionPassWord(String password) {
            this.password = password;
        }
    }

    /**
     * 判断密码相等
     * @param password
     * @return
     */
    public Boolean isEqual(String password) {
        return this.encryptionPassWord.password.equals(getEncryptionPassWord(password));
    }

}
