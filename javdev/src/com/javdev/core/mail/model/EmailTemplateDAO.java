package com.javdev.core.mail.model;

import java.util.List;

import org.hibernate.Query;

import com.javdev.core.api.IState;
import com.javdev.core.connection.model.ConfigHibernateDAO;

public class EmailTemplateDAO extends ConfigHibernateDAO {

	/** @author MTorres 22 de abr. de 2016 12:55:35 */
	public List<EmailTemplate> loadAll() throws Exception {
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" from EmailTemplate et WHERE et.state = :activeState ");
			qo = getSession().createQuery(hql.toString());
			qo.setParameter("activeState", IState.ACTIVE);
			return qo.list();
		} catch (Exception e) {
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}

}
