package kr.co.moments.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Sha512SaltUtil {
	   private static final String CHAR_POOL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    private static final SecureRandom random = new SecureRandom();

	    public static String generateSalt(int length) {
	        StringBuilder sb = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int idx = random.nextInt(CHAR_POOL.length());
	            sb.append(CHAR_POOL.charAt(idx));
	        }
	        return sb.toString();
	    }
	    

	    public static String hashWithSalt(String salt, String password) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");
	            md.update(salt.getBytes(StandardCharsets.UTF_8));
	            byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
	            
	            // byte[] → hex 문자열
	            StringBuilder sb = new StringBuilder(2 * digest.length);
	            for (byte b : digest) {
	                sb.append(String.format("%02x", b));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new IllegalStateException("SHA-512 알고리즘을 찾을 수 없습니다.", e);
	        }
	    }
}