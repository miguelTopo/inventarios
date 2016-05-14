package com.javdev.core.connection.model;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javdev.core.connection.util.HibernateUtil;

public class ConfigHibernateDAO {

	private Session session;

	public ConfigHibernateDAO() {

	}

	/** @author MTorres 7/01/2016 9:43:01 a. m. */
	public Session getSession() throws Exception {
		try {
			if (this.session == null)
				this.session = HibernateUtil.getSessionFactory().openSession();
			return session;
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 22 de abr. de 2016 11:18:25 */
	public boolean loadFieldExist(String table, String field, String fieldValue) throws Exception {
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" SELECT COUNT(t.id) ");
			hql.append(" FROM  ");
			hql.append(table);
			hql.append(" t ");
			hql.append(" WHERE t.");
			hql.append(field);
			hql.append(" = :fieldValue ");

			qo = getSession().createQuery(hql.toString());
			qo.setParameter("fieldValue", fieldValue);
			qo.setMaxResults(1);
			Object o = qo.uniqueResult();
			return o != null ? true : Integer.parseInt(o.toString()) > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}
}
