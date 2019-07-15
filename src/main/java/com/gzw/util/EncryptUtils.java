package com.gzw.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * jasypt加密
 * @author sht
 *
 */
public class EncryptUtils {
	// 密钥
		private static final String KEY = "shanghaimilk";
		
		public static void main(String[] args) {
			String ciphertext1 = encrypt("hhsy");
			//System.out.println(ciphertext1);
			String text1 = decrypt(ciphertext1);
			//System.out.println(text1);               // abcdefg
		}
		
		/**
		 * 加密
		 * @param text 明文
		 * @return     密文
		 */
		public static String encrypt(String text) {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setAlgorithm("PBEWithMD5AndDES");
			encryptor.setPassword(KEY);
			return encryptor.encrypt(text);
		}
		
		/**
		 * 解密
		 * @param ciphertext 密文
		 * @return           明文
		 */
		public static String decrypt(String ciphertext) {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(KEY);
			encryptor.setAlgorithm("PBEWithMD5AndDES");
			return encryptor.decrypt(ciphertext);
		}
}
