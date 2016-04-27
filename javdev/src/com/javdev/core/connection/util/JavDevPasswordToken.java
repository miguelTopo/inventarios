package com.javdev.core.connection.util;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

public class JavDevPasswordToken {

	@Id @GenericGenerator(name = "keyGenerator", strategy = "increment") @GeneratedValue(generator = "keyGenerator") @Column(name = "id",
		nullable = false) private Long id;
	@Column(name = "username", nullable = false) private String username;
	@Column(name = "email", nullable = false) private String email;
	@Column(name = "hashPassword", nullable = false) @Getter @Setter private String hashPassword;
	@Column(name = "salt", nullable = false) @Getter @Setter private String salt;

	public JavDevPasswordToken() {

	}

	public JavDevPasswordToken(String hashPassword, String salt) {
		this.hashPassword = hashPassword;
		this.salt = salt;
	}
}
