package com.javdev.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.primefaces.context.RequestContext;

import com.javdev.core.list.controller.BeanList;
import com.javdev.core.pojo.DocumentType;
import com.javdev.core.pojo.Gender;
import com.javdev.core.pojo.Role;
import com.sun.mail.iap.Response;

public abstract class BackingBean implements Serializable {

	public BackingBean() {
		try {
			Factory<SecurityManager> factory = new IniSecurityManagerFactory();
			SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 7/01/2016 4:35:05 p. m. */
	public void logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
			redirectToLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected RequestContext getRequestContext() throws Exception {
		try {
			return RequestContext.getCurrentInstance();
		} catch (Exception e) {
			throw e;
		}
	}
	/** @author MTorres 11/01/2016 9:44:26 p. m. */
	private String getServerPath() throws Exception {
		try {
			HttpServletRequest sr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String schema = sr.getScheme();// http
			String serverName = sr.getServerName();// localhost o dirección de
													// servidor
			int port = sr.getServerPort();// Puerto
			String context = sr.getContextPath();// context Project Name
			return schema + "://" + serverName + ":" + port + context;
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 11/01/2016 9:38:17 p. m. */
	public void redirectTo(String urlRequest) {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("INFO: REDIRECT TO: " + getServerPath() + urlRequest);
			context.redirect(getServerPath() + urlRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 12/01/2016 10:40:31 p. m. */
	public void execute(String methodRun) {
		try {
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.execute(methodRun);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 11/01/2016 10:17:29 p. m. */
	public String getCurrentUser() throws Exception {
		try {
			return "admin";
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 11/01/2016 10:17:29 p. m. */
	public String getCurrentSession() throws Exception {
		try {
			return "admin";
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 7/01/2016 4:41:50 p. m. */
	public void redirectToIndex() throws Exception {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("INFO: REDIRECT TO: " + getServerPath() + "portal/inicio");
			context.redirect(getServerPath() + "portal/inicio");
		} catch (Exception e) {
			throw e;
		}

	}

	/** @author MTorres 11/01/2016 8:09:44 p. m. */
	public void redirectToLogin() throws Exception {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("INFO: REDIRECT TO: " + getServerPath() + "/main/login");
			context.redirect(getServerPath() + "/main/login");
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 5/01/2016 4:22:46 p. m. */
	public void addInfoMessage(String summary, String detail) throws Exception {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 5/01/2016 4:22:46 p. m. */
	public void addErrorMessage(String summary, String detail) throws Exception {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 5/01/2016 4:22:46 p. m. */
	public void addFatalMessage(String summary, String detail) throws Exception {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail));
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 5/01/2016 4:22:46 p. m. */
	public void addWarnMessage(String summary, String detail) throws Exception {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Gender> getGenderList() {
		return BeanList.getGenderList();
	}

	public List<DocumentType> getDocumentTypeList() {
		return BeanList.getDocumentTypeList();
	}

	public List<Role> getRoleList() {
		return BeanList.getRoleList();
	}

}
