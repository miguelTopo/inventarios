package com.javdev.core.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.javdev.core.model.AParameter;
import com.javdev.core.system.model.SystemController;

@Entity
@Table(name = "ActionDB", schema = "javdev")
public class ActionDB extends AParameter {

	public ActionDB() throws Exception {
		try {

		} catch (Exception e) {
			throw e;
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

	@Column(name = "state", nullable = false)
	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	@Transient
	public SystemController getSystemController() {
		return this.systemController;
	}

	public void setSystemController(SystemController systemController) {
		this.systemController = systemController;
	}


}
