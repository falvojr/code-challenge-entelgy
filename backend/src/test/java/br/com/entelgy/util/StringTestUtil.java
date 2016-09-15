package br.com.entelgy.util;

import java.security.SecureRandom;

/**
 * Useful class for {@link String} tests needs.
 * 
 * @author falvojr
 */
public class StringTestUtil {

	private static final String SYMBOLS = "0123456789 ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	private StringTestUtil() {
		super();
	}

	public static String randomString(int length) {
		final StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(SYMBOLS.charAt(SECURE_RANDOM.nextInt(SYMBOLS.length())));
		}
		return sb.toString();
	}
}
