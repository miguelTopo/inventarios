package com.javdev.core.session.model;

import java.io.Serializable;
import java.util.List;

import com.javdev.core.pojo.UserRole;

import lombok.Getter;
import lombok.Setter;

public class CurrentSession implements Serializable {

	@Getter @Setter private Long idSystemUser;
	@Getter @Setter private Long idDocumentType;
	@Getter @Setter private String email;
	@Getter @Setter private String name;
	@Getter @Setter private String documentNumber;
	@Getter @Setter private String lastName;
	@Getter @Setter private List<UserRole> roleList;

	public CurrentSession() {

	}


}
