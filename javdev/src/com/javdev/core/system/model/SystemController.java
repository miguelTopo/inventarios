package com.javdev.core.system.model;

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
@Table(name = "SystemController", schema = "javdev")
public class SystemController implements Serializable {

	@Id @GenericGenerator(name = "keyGenerator", strategy = "increment") @GeneratedValue(generator = "keyGenerator") @Column(name = "id",
		nullable = false) @Getter @Setter private Long id;
	@Column(name = "dateInsert", nullable = false) @Getter @Setter private String dateInsert;
	@Column(name = "hourInsert", nullable = false) @Getter @Setter private String hourInsert;
	@Column(name = "userPerform", nullable = false) @Getter @Setter private String userPerform;
	@Column(name = "tableName", nullable = false) @Getter @Setter private String tableName;
	@Column(name = "idActionDB", nullable = false) @Getter @Setter private Long idActionDB;
	@Column(name = "idTable", nullable = false) @Getter @Setter private Long idTable;

	public SystemController() {

	}

	public SystemController(String dateInsert, String hourInsert, String userPerform, String tableName, Long idActionDB, Long idTable) {
		this.dateInsert = dateInsert;
		this.hourInsert = hourInsert;
		this.userPerform = userPerform;
		this.tableName = tableName;
		this.idActionDB = idActionDB;
		this.idTable = idTable;
	}
}
