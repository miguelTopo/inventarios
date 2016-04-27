package com.javdev.core.pojo;

import java.util.List;

import org.hibernate.Query;

import com.javdev.core.api.IJavDevState;
import com.javdev.core.connection.dao.ConfigHibernateDAO;

public class DocumentTypeDAO extends ConfigHibernateDAO {

	public DocumentTypeDAO() {

	}

	/** @author MTorres 7/01/2016 11:28:25 a. m. */
	public List<DocumentType> loadAll() throws Exception{
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" from DocumentType dt WHERE dt.state = :activeState ");
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
