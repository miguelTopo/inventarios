package com.javdev.core.mail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.javdev.core.model.AParameter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EmailTemplate", schema = "javdev")
public class EmailTemplate extends AParameter {

	@Getter @Setter @Column(name = "subject", nullable = false) private String subject;
	@Getter @Setter @Column(name = "bodyTemplate", nullable = false) private String bodyTemplate;

	public EmailTemplate() {

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



}
