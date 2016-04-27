package com.javdev.core.dao;


import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.javdev.core.api.IJavDevState;
import com.javdev.core.connection.dao.ConfigHibernateDAO;
import com.javdev.core.pojo.JavDevUser;
import com.javdev.core.realm.model.JavDevToken;
import com.javdev.core.util.DateUtil;

public class UserDAO extends ConfigHibernateDAO {

	public UserDAO() {

	}

	/** @author MTorres 7/01/2016 9:47:25 a. m. */
	public JavDevToken getUserByEmail(String email) throws Exception {
		Query qo = null;
		StringBuilder hql = new StringBuilder();
		try {
			hql.append(" SELECT jdt.id AS id, ");
			hql.append(" jdt.username AS username, ");
			hql.append(" jdt.email AS email, ");
			hql.append(" jdt.hashPassword AS hashPassword, ");
			hql.append(" jdt.salt AS salt ");
			hql.append(" FROM JavDevToken jdt WHERE jdt.email = :email ");

			qo = getSession().createQuery(hql.toString()).setResultTransformer(Transformers.aliasToBean(JavDevToken.class));
			qo.setParameter("email", email);
			qo.setMaxResults(1);
			return (JavDevToken) qo.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** @author MTorres 11/01/2016 10:21:39 p. m. */
	public boolean userDelete(JavDevUser user, String userChange) throws Exception {
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" UPDATE JavDevUser jdu ");
			hql.append(" SET jdu.state = :invalidState, ");
			hql.append(" jdu.userChange = :userChange, ");
			hql.append(" jdu.dateChange = :dateChange, ");
			hql.append(" jdu.hourChange = :hourChange ");
			hql.append(" WHERE jdu.id = :idJavDevUser ");

			qo = getSession().createQuery(hql.toString());
			qo.setParameter("invalidState", IJavDevState.INACTIVE);
			qo.setParameter("userChange", userChange);
			qo.setParameter("dateChange", DateUtil.getCurrentStringDate());
			qo.setParameter("hourChange", DateUtil.getCurrentStringHour());
			qo.setParameter("idJavDevUser", user.getId());

			int rowUpdate = qo.executeUpdate();
			return rowUpdate == 1;
		} catch (Exception e) {
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}

}
