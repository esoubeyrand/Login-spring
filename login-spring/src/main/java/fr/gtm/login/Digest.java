package fr.gtm.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Repository;

public class Digest {

	public static String encode(String pw) throws NoSuchAlgorithmException {

		String toEncode = pw;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] encode = md.digest(toEncode.getBytes());
		System.out.println(bytesToHex(encode));
		String hex = bytesToHex(encode);
		return hex;

	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
