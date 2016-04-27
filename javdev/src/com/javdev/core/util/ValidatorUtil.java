package com.javdev.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** @author MTorres 7/01/2016 1:09:46 p. m. */
	public static boolean validEmail(String email) throws Exception {
		try {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		} catch (Exception e) {
			throw e;
		}
	}

}
