package dev.galliard.contasoc.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class PasswordUtils {
	public static byte[] encrypt(String plainText, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
    	InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(plainText.getBytes());
	}
	
    public static String decrypt(byte[] encryptedText, PrivateKey privateKey) throws NoSuchAlgorithmException,
    	NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    	Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedText);
    	return new String(decryptedBytes);
}
}
