package com.javdev.core.realm.util;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import lombok.Getter;
import lombok.Setter;

public class JavDevSaltedAuthenticationInfo implements SaltedAuthenticationInfo {

	private final String username;
	private final String password;
	private final String salt;

	public JavDevSaltedAuthenticationInfo(String username, String password, String salt) {
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public Object getCredentials() {
		return password;
	}

	public PrincipalCollection getPrincipals() {
		PrincipalCollection coll = new SimplePrincipalCollection(username, username);
		return coll;
	}

	public ByteSource getCredentialsSalt() {
		return new SimpleByteSource(Base64.decode(salt));
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}
}
