package com.javdev.core.realm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.javdev.core.model.AParameter;
import com.javdev.core.system.model.SystemController;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "JavDevToken", schema = "javdev")
public class JavDevToken extends AParameter {

	@Getter @Setter @Column private Long idSystemUser;
	@Getter @Setter @Column private String username;
	@Getter @Setter @Column private String email;
	@Getter @Setter @Column private String hashPassword;
	@Getter @Setter @Column private String salt;

	public JavDevToken() {

	}

	public JavDevToken(Long id, String username, String email, String hashPassword, String salt) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.hashPassword = hashPassword;
		this.salt = salt;
	}

	@Id
	@GenericGenerator(name = "keyGenerator", strategy = "increment")
	@GeneratedValue(generator = "keyGenerator")
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;

	}

	@Column(name = "state", nullable = false)
	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
		this.state = state;
	}
	
	public SystemController getSystemController() {
		return this.systemController;
	}

	public void setSystemController(SystemController systemController) {
		this.systemController = systemController;
	}


}
