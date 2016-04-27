package com.javdev.core.list.controller;

import java.util.List;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.javdev.core.controller.Controller;
import com.javdev.core.pojo.DocumentType;
import com.javdev.core.pojo.Gender;
import com.javdev.core.pojo.Role;

import lombok.Getter;

@ManagedBean(eager = true)
@ApplicationScoped
public class BeanList {

	@Getter private static List<Gender> genderList;
	@Getter private static List<DocumentType> documentTypeList;
	@Getter private static List<Role> roleList;
	
	//Controller
	private static Controller controller = new Controller();

	static {
		setUpJavDevList();
	}

	/** @author MTorres 7/01/2016 11:16:39 a. m. */
	private static void setUpJavDevList() {
		try {
			loadRoleList();
			loadGenderList();
			loadDocumentTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadRoleList() throws Exception{
		try {
			if(roleList==null){
				roleList=controller.loadRoleList();
			}
		} catch (Exception e) {
			throw e;
		}
		
	}

	/** @author MTorres 7/01/2016 11:18:41 a. m. */
	private static void loadDocumentTypeList() {
		try {
			if (documentTypeList == null) {
				documentTypeList = controller.loadDocumentTypeList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 7/01/2016 11:32:21 a. m. */
	private static void loadGenderList() {
		try {
			if (genderList == null)
				genderList = controller.loadGenderList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
