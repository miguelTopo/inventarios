package com.javdev.core.pojo;

import java.util.List;

import org.hibernate.Query;

import com.javdev.core.api.IState;
import com.javdev.core.connection.model.ConfigHibernateDAO;



public class GenderDAO extends ConfigHibernateDAO {

	public GenderDAO() {

	}

	/** @author MTorres 7/01/2016 11:34:07 a. m. */
	public List<Gender> loadAll() throws Exception{
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" from Gender g WHERE g.state = :activeState ");
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
