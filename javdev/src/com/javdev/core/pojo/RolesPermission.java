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
@Table(schema = "javdev", name = "RolesPermission")
public class RolesPermission implements Serializable {

	@Getter @Setter @Id @GenericGenerator(name = "keyGenerator",
		strategy = "increment") @GeneratedValue(generator = "keyGenerator") @Column(name = "id") private Long id;
	@Getter @Setter @Column(name = "permission") private String permission;
	@Getter @Setter @Column(name = "roleName") private String roleName;

	public RolesPermission() {

	}

	public RolesPermission(Long id, String permission, String roleName) {
		this.id = id;
		this.permission = permission;
		this.roleName = roleName;
	}



}
