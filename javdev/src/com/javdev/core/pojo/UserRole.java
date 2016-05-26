package com.javdev.core.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "javdev", name = "UserRole")
public class UserRole implements Serializable {

	@Getter @Setter @Id @GenericGenerator(name = "keyGenerator",
		strategy = "increment") @GeneratedValue(generator = "keyGenerator") @Column(name = "id", nullable = false) private Long id;
	@Getter @Setter @Column(name = "idSystemUser", nullable = false) private Long idSystemUser;
	@Getter @Setter @Column(name = "idRole", nullable = false) private Long idRole;
	@Getter @Setter @Column(name = "email", nullable = false) private String email;

	private transient String roleName;


	public UserRole() {

	}
	

	@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
