package com.javdev.core.pojo;



import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.javdev.core.model.AParameter;
import com.javdev.core.realm.model.JavDevToken;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "JavDevUser", schema = "javdev")
public class JavDevUser extends AParameter {

	@Getter @Setter @Column(name = "idDocumentType", nullable = false) private Long idDocumentType;
	@Getter @Setter @Column(name = "idGender", nullable = false) private Long idGender;
	@Getter @Setter @Column(name = "lastname", nullable = true) private String lastname;
	@Getter @Setter @Column(name = "documentNumber", nullable = false) private String documentNumber;
	@Getter @Setter @Column(name = "birthday", nullable = false) private String birthday;

	private transient JavDevToken token;
	private transient String password;
	private transient String retryPassword;
	private transient String email;
	private transient String nameDocumentType;
	private transient List<String> idRoleList;
	private transient Date birthdayDate;

	public JavDevUser() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Column(name = "dateInsert", nullable = false)
	public String getDateInsert() {
		return this.dateInsert;
	}

	public void setDateInsert(String dateInsert) {
		this.dateInsert = dateInsert;
	}

	@Column(name = "hourInsert", nullable = false)
	public String getHourInsert() {
		return this.hourInsert;
	}

	public void setHourInsert(String hourInsert) {
		this.hourInsert = hourInsert;
	}

	@Column(name = "dateUpdate")
	public String getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Column(name = "hourUpdate")
	public String getHourUpdate() {
		return this.hourUpdate;
	}

	public void setHourUpdate(String hourUpdate) {
		this.hourUpdate = hourUpdate;
	}

	@Column(name = "userInsert", nullable = false)
	public String getUserInsert() {
		return this.userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}

	@Column(name = "userUpdate")
	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	@Column(name = "state", nullable = false)
	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
		this.state = state;
	}


	@Transient
	public JavDevToken getToken() {
		if (token == null)
			token = new JavDevToken();
		return token;
	}

	public void setToken(JavDevToken token) {
		this.token = token;
	}

	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getRetryPassword() {
		return retryPassword;
	}

	public void setRetryPassword(String retryPassword) {
		this.retryPassword = retryPassword;
	}

	@Transient
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	@Transient
	public String getNameDocumentType() {
		return nameDocumentType;
	}

	public void setNameDocumentType(String nameDocumentType) {
		this.nameDocumentType = nameDocumentType;
	}
	
	@Transient
	public List<String> getIdRoleList() {
		return idRoleList;
	}

	public void setIdRoleList(List<String> idRoleList) {
		this.idRoleList = idRoleList;
	}

}
