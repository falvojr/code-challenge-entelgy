package br.com.entelgy.util;

import java.security.SecureRandom;

/**
 * Useful class for {@link String} tests needs.
 * 
 * @author falvojr
 */
public class StringTestUtil {

	/**
	 * Multiples spaces for increase your chances.
	 */
	private static final String SYMBOLS = "01234 56789 ABCDE FGHIJ KLMNO PQRST UVWXY Zabcd efghi jklmn opqrs tuvwx yz";
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
