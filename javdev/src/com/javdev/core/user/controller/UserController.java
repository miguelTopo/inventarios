package com.javdev.core.user.controller;

import java.util.List;


import org.hibernate.Transaction;

import com.javdev.core.connection.util.JavDevPasswordToken;
import com.javdev.core.controller.Controller;
import com.javdev.core.controller.ValidationMessage;
import com.javdev.core.dao.UserDAO;
import com.javdev.core.pojo.SystemUser;
import com.javdev.core.security.util.PasswordUtil;
import com.javdev.core.util.DateUtil;
import com.javdev.core.util.ValidatorUtil;

public class UserController extends Controller {

	/** @author MTorres 6/01/2016 10:09:34 p. m. */
	public ValidationMessage validSaveUser(SystemUser user) throws Exception {
		try {
			if (user == null)
				return addWarnMessage("Crear usuario", "Por favor diligencie el formulario.");
			else if (user.getName() == null || user.getName().trim().isEmpty())
				return addWarnMessage("Crear usuario", "Debe digitar un nombre.");
			else if (user.getLastname() == null || user.getLastname().trim().isEmpty())
				return addWarnMessage("Crear usuario", "Debe digitar un apellido.");
			else if (user.getIdDocumentType() == null || user.getIdDocumentType().equals(0L))
				return addWarnMessage("Crear usuario", "Debe seleccionar un tipo de documento.");
			else if (user.getDocumentNumber() == null || user.getDocumentNumber().trim().isEmpty())
				return addWarnMessage("Crear usuario", "Debe digitar un número de documento.");
			else if (user.getEmail() == null || user.getEmail().trim().isEmpty())
				return addWarnMessage("Crear usuario", "Debe digitar un correo electrónico.");
			else if (!ValidatorUtil.validEmail(user.getEmail()))
				return addWarnMessage("Crear usuario", "El correo electrónico ingresado es inválido.");
			else if (user.getBirthdayDate() == null)
				return addWarnMessage("Crear usuario", "Debe seleccionar una fecha de nacimiento.");
			else if (user.getIdGender() == null || user.getIdGender().equals(0L))
				return addWarnMessage("Crear usuario", "Debe seleccionar un género.");
			else if (user.getPassword() == null || user.getPassword().trim().isEmpty())
				return addWarnMessage("Crear usuario", "Debe digitar una contraseña.");
			else if (user.getRetryPassword() == null || user.getRetryPassword().trim().isEmpty())
				return addWarnMessage("Crear usuario", "Por favor confirme la contraseña en el campo correspondiente.");
			else if (!user.getPassword().equals(user.getRetryPassword()))
				return addWarnMessage("Crear usuario", "Las contraseñas no coinciden, por favor valide.");
			else if(user.getIdRoleList()==null || user.getIdRoleList().isEmpty())
				return addWarnMessage("Crear usuario", "Debe seleccionar por lo menos un rol para el usuario.");
			else
				return addInfoMessage("", "");
		} catch (Exception e) {
			throw e;
		}

	}

	/** @author MTorres 7/01/2016 11:06:41 a. m. */
	public boolean saveUser(SystemUser user) throws Exception {
		UserDAO dao = new UserDAO();
		Transaction tx = dao.getSession().beginTransaction();
		try {
			user.setBirthday(DateUtil.formatDate(user.getBirthdayDate(), DateUtil.YYYY_MM_DD));
			JavDevPasswordToken jdpt = PasswordUtil.generatePassword(user.getPassword());
			user.getToken().setHashPassword(jdpt.getHashPassword());
			user.getToken().setSalt(jdpt.getSalt());
			user.getToken().setUsername(user.getEmail());
			user.getToken().setEmail(user.getEmail());

			// Save User
//			user.initialize(user.getId() == null || user.getId().equals(0L));
			dao.getSession().save(user);

			// Save Token
//			user.getToken().initialize(user.getToken().getId() == null || user.getToken().getId().equals(0L));
			user.getToken().setIdSystemUser(user.getId());
			dao.getSession().save(user.getToken());

			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			dao.getSession().cancelQuery();
			tx.rollback();
			throw e;
		} finally {
			dao.getSession().close();
			tx = null;
			dao = null;
		}
	}

	public List<SystemUser> loadUserList() throws Exception {
		UserDAO dao = new UserDAO();
		try {
			//return dao.loadUserList();
			return null;
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}
	}

	/** @author MTorres 11/01/2016 10:19:57 p. m. */
	public boolean userDelete(SystemUser user, String userChange) throws Exception {
		UserDAO dao = new UserDAO();
		Transaction tx = dao.getSession().beginTransaction();
		try {
			boolean success = dao.userDelete(user, userChange);
			if (success)
				tx.commit();
			return success;
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}

	}


}
