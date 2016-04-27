package com.javdev.core.realm.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import com.javdev.core.dao.UserDAO;
import com.javdev.core.realm.model.JavDevToken;

public class JavDevCustomRealm extends JdbcRealm {

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();

		if (username == null) {
			System.out.println("username is null");
			return null;
		}
		UserDAO dao = new UserDAO();
		try {
			dao.getSession().beginTransaction();
			final JavDevToken user = dao.getUserByEmail(username);

			if (user == null) {
				System.out.println("No account found for user [" + username + "]");
				return null;
			}
			JavDevSaltedAuthenticationInfo info = new JavDevSaltedAuthenticationInfo(username, user.getHashPassword(), user.getSalt());
			return info;
		} catch (Exception e) {
			System.out.println("No se pudo ejecutar SaltedAuthenticationInfo JavDevCustomRealm...");
			return null;
		} finally {
			try {
				dao.getSession().getTransaction().commit();
				if (dao.getSession().isOpen())
					dao.getSession().close();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
