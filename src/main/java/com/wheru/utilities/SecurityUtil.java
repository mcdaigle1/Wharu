package com.wheru.utilities;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
 
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecurityUtil {
	
	public enum EncryptionTypes {
		PBKDF2("PBKDF2WithHmacSHA1"),
		TRIPLE_DES("DESede");
		
		private String algorithmName; 
		
		EncryptionTypes(String algName) { algorithmName = algName; }
		
		public String getAlgorithmName() { return algorithmName; };
	};

	// Encrypt passwords using PBKDF2 encryption.  
	public static byte[] encryptPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
		// specifically names SHA-1 as an acceptable hashing algorithm for
		// PBKDF2
		//String algorithm = "PBKDF2WithHmacSHA1";
		String algorithm = EncryptionTypes.PBKDF2.getAlgorithmName();
		// SHA-1 generates 160 bit hashes, so that's what makes sense here
		int derivedKeyLength = 160;
		// Pick an iteration count that works for you. The NIST recommends at
		// least 1,000 iterations:
		// http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
		// iOS 4.x reportedly uses 10,000:
		// http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
		int iterations = 20000;

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		return f.generateSecret(spec).getEncoded();
	}
	
	// Why a separate encryption method here?  We want to be able to encrypt and decrypt some text,
	// however, we don't want to ever decrypt (or have the ability to decrypt) passwords.  So we use
	// Triple DES encryption and provide a decrypt method for other types of information
	public static byte[] encryptDES(String unencryptedString, byte[] salt) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
				InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] unencryptedText = unencryptedString.getBytes("UTF-8");
		String algorithm = EncryptionTypes.TRIPLE_DES.getAlgorithmName();
		Cipher cipher = Cipher.getInstance(algorithm);
		KeySpec keySpec = new DESedeKeySpec(salt);
		//SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(unencryptedText);
    }

	// Decrypt Triple DES encrypted text
    public static String decryptDES(byte[] encryptedText, byte[] salt) 
    		throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
    			InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    	//String algorithm = EncryptionTypes.TRIPLE_DES.getAlgorithmName();
    	Cipher cipher = Cipher.getInstance("DESede");
		KeySpec keySpec = new DESedeKeySpec(salt);
		//SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);		
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(encryptedText);
        return new String(decryptedText, "UTF-8");
    }

	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		return generateSalt(8);
	}
	
	public static byte[] generateSalt(Integer size) throws NoSuchAlgorithmException {
		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[size];
		random.nextBytes(salt);

		return salt;
	}
}


