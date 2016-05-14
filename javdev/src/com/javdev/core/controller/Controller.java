package com.javdev.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transaction;

import com.javdev.core.connection.model.ConfigHibernateDAO;
import com.javdev.core.dao.UserDAO;
import com.javdev.core.pojo.DocumentType;
import com.javdev.core.pojo.DocumentTypeDAO;
import com.javdev.core.pojo.Gender;
import com.javdev.core.pojo.GenderDAO;
import com.javdev.core.pojo.Role;
import com.javdev.core.pojo.RoleDAO;

public class Controller extends ConfigHibernateDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** @author MTorres 22 de abr. de 2016 11:21:01 */

	protected ValidationMessage addWarnMessage(String summary, String detail) throws Exception {
		try {
			ValidationMessage vm = new ValidationMessage(false, summary, detail);
			return vm;
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 6/01/2016 10:23:42 p. m. */
	protected ValidationMessage addInfoMessage(String title, String summary) throws Exception {
		try {
			ValidationMessage vm = new ValidationMessage(true, title, summary);
			return vm;
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 7/01/2016 11:20:07 a. m. */
	public List<DocumentType> loadDocumentTypeList() throws Exception {
		DocumentTypeDAO dao = new DocumentTypeDAO();
		try {
			return dao.loadAll();
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}
	}

	/** @author MTorres 7/01/2016 11:32:40 a. m. */
	public List<Gender> loadGenderList() throws Exception {
		GenderDAO dao = new GenderDAO();
		try {
			return dao.loadAll();
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}
	}


	/** @author MTorres 12/01/2016 11:43:08 p. m. */
	public boolean loadFieldExist(String table, String field, String fieldValue) throws Exception {
		ConfigHibernateDAO dao = new ConfigHibernateDAO();
		try {
			return dao.loadFieldExist(table, field, fieldValue);
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}
	}

	/** @author MTorres 22 de abr. de 2016 12:55:49 */
	public List<Role> loadRoleList() throws Exception {
		RoleDAO dao = new RoleDAO();
		try {
			return dao.loadAll();
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}
	}

}
