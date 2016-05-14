package com.javdev.core.pojo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.javdev.core.api.IJavDevState;
import com.javdev.core.connection.dao.ConfigHibernateDAO;

public class JavDevUserDAO extends ConfigHibernateDAO {

	public JavDevUserDAO() {}

	public List<JavDevUser> loadUserList() throws Exception{
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" SELECT jdu.id AS id, ");
			hql.append(" jdu.idDocumentType AS idDocumentType, ");
			hql.append(" jdu.idGender AS idGender, ");
			hql.append(" jdu.name AS name, ");
			hql.append(" jdu.lastname AS lastname, ");
			hql.append(" jdu.documentNumber AS documentNumber, ");
			hql.append(" jdu.birthday AS birthday, ");
			hql.append(" dt.name AS nameDocumentType, ");
			hql.append(" jdt.email AS email ");
			hql.append(" FROM JavDevUser jdu, ");
			hql.append(" DocumentType dt, ");
			hql.append(" JavDevToken jdt ");
			hql.append(" WHERE dt.id = jdu.idDocumentType ");
			hql.append(" AND jdt.idJavDevUser=  jdu.id ");
			hql.append(" AND jdu.state = :state ");

			qo = getSession().createQuery(hql.toString()).setResultTransformer(Transformers.aliasToBean(JavDevUser.class));
			qo.setParameter("state", IJavDevState.ACTIVE);
			return qo.list();
		} catch (Exception e) {
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}
}
