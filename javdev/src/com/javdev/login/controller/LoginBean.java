package com.javdev.login.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.javdev.core.controller.BackingBean;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {@URLMapping(id = "login", pattern = "/main/login", viewId = "/pages/external/main/login.jsf"),
	@URLMapping(id = "index", pattern = "/portal/inicio", viewId = "/pages/authenticated/index.jsf")})

public class LoginBean extends BackingBean {

	@Getter @Setter private String username;
	@Getter @Setter private String password;

	public LoginBean() throws Exception{
		try {
			// Factory<SecurityManager> factory = new IniSecurityManagerFactory();
			// SecurityManager securityManager = factory.getInstance();
			// SecurityUtils.setSecurityManager(securityManager);
		} catch (Exception e) {
			throw e;
		}
	}

	@URLAction(onPostback = true, mappingId = "login")
	private void initializeLoginBean() {
		try {
			this.username = null;
			this.password = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres */
	public void tryLogin() {
		try {
			if (!validateTrylogin())
				return;

			Subject currentUser = SecurityUtils.getSubject();

			if (!currentUser.isAuthenticated()) {
				// collect user principals and credentials in a gui specific manner
				// such as username/password html form, X509 certificate, OpenID, etc.
				// We'll use the username/password example here since it is the most common.
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				// this is all you have to do to support 'remember me' (no config - built in!):
				token.setRememberMe(false);

				try {
					currentUser.login(token);
					System.out.println("User [" + currentUser.getPrincipal().toString() + "] logged in successfully.");
					// save current username in the session, so we have access to our User model
					currentUser.getSession().setAttribute("username", username);
			 redirectToIndex();
				} catch (UnknownAccountException uae) {
					System.out.println("There is no user with username of " + token.getPrincipal());
					
				} catch (IncorrectCredentialsException ice) {
					System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
			
				} catch (LockedAccountException lae) {
					System.out.println(
						"The account for username " + token.getPrincipal() + " is locked.  " + "Please contact your administrator to unlock it.");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres */
	private boolean validateTrylogin() throws Exception{
		try {
			if (this.username == null || this.username.trim().isEmpty()) {
				addWarnMessage("Ingresar", "debe ingresar un usuario");
				return false;
			} else if (this.password == null || this.password.trim().isEmpty()) {
				addWarnMessage("Ingresar", "debe ingresar una contraseña");
				return false;
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}
