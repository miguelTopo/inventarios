package com.javdev.core.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "javdev", name = "JavDevUserRole")
public class JavDevUserRole implements Serializable {

	@Getter @Setter @Id @GenericGenerator(name = "keyGenerator",
		strategy = "increment") @GeneratedValue(generator = "keyGenerator") @Column private Long id;
	@Getter @Setter @Column private String roleName;
	@Getter @Setter @Column private String email;

	public JavDevUserRole() {

	}

	public JavDevUserRole(Long id, String roleName, String email) {
		this.id = id;
		this.roleName = roleName;
		this.email = email;
	}

}
