package com.javdev.core.security.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import com.javdev.core.connection.util.JavDevPasswordToken;

public class PasswordUtil {

	public static JavDevPasswordToken generatePassword(String plainTextPassword) throws Exception{
		try {
			RandomNumberGenerator rng = new SecureRandomNumberGenerator();
			Object salt = rng.nextBytes();
			// Now hash the plain-text password with the random salt and multiple
			// iterations and then Base64-encode the value (requires less space than
			// Hex):
			String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();
			System.out.println(hashedPasswordBase64);

			JavDevPasswordToken jdpt = new JavDevPasswordToken(hashedPasswordBase64, salt.toString());
			return jdpt;
		} catch (Exception e) {
			throw e;
		}
	}

}
