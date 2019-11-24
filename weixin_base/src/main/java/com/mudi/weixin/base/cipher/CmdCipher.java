package com.mudi.weixin.base.cipher;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CmdCipher {
    private static final String PASSWORD = "vCb01hOGGj7W7z49B/4OFsEB2hzmE22J";
    public static final String KEY_ALGORITHM = "DESede";
    private static SecretKey secret = null;

    public CmdCipher() {
        try {
            DESedeKeySpec spec = new DESedeKeySpec(Base64.getDecoder().decode(PASSWORD));
            SecretKeyFactory facotory = SecretKeyFactory.getInstance("DESede");
            secret = facotory.generateSecret(spec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secret);
        return cipher.doFinal(data);
    }
}
