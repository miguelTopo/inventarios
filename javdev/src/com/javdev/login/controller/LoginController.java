package com.javdev.login.controller;

import com.javdev.core.controller.Controller;
import com.javdev.core.pojo.SystemUserDAO;
import com.javdev.core.session.model.CurrentSession;

public class LoginController extends Controller {

	public CurrentSession loadUser(String username) throws Exception {
		SystemUserDAO dao = new SystemUserDAO();
		try {
			return dao.loadUser(username);
		} catch (Exception e) {
			dao.getSession().cancelQuery();
			throw e;
		} finally {
			dao.getSession().close();
			dao = null;
		}
	}



}
