package com.javdev.core.pojo;

import java.util.List;

import org.hibernate.Query;

import com.javdev.core.api.IJavDevState;
import com.javdev.core.connection.dao.ConfigHibernateDAO;

public class RoleDAO extends ConfigHibernateDAO {

	/** @author MTorres 22 de abr. de 2016 12:55:35 */
	public List<Role> loadAll() throws Exception {
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" from Role r WHERE r.state = :activeState ");
			qo = getSession().createQuery(hql.toString());
			qo.setParameter("activeState", IJavDevState.ACTIVE);
			return qo.list();
		} catch (Exception e) {
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}
}
