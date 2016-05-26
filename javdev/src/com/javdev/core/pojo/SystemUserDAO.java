package com.javdev.core.pojo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.hibernate.transform.Transformers;

import com.javdev.core.api.IState;
import com.javdev.core.connection.model.ConfigHibernateDAO;
import com.javdev.core.session.model.CurrentSession;

public class SystemUserDAO extends ConfigHibernateDAO {

	public SystemUserDAO() {}

	public List<SystemUser> loadUserList() throws Exception {
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

			qo = getSession().createQuery(hql.toString()).setResultTransformer(Transformers.aliasToBean(SystemUser.class));
			qo.setParameter("state", IState.ACTIVE);
			return qo.list();
		} catch (Exception e) {
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}

	/** @author MTorres 23/05/2016 11:17:11 p. m. */
	public CurrentSession loadUser(String username) throws Exception {
		StringBuilder hql = new StringBuilder();
		Query qo = null;
		try {
			hql.append(" SELECT su.id AS idSystemUser, ");
			hql.append(" su.idDocumentType AS idDocumentType, ");
			hql.append(" su.name AS name, ");
			hql.append(" su.lastname AS lastname, ");
			hql.append(" su.documentNumber AS documentNumber ");
			hql.append(" FROM SystemUser su, ");
			hql.append(" JavDevToken jdt ");
			hql.append(" WHERE jdt.idSystemUser = su.id ");
			hql.append(" AND jdt.username = :username ");
			hql.append(" AND su.state = :state ");
			hql.append(" AND jdt.state = :state ");

			qo = getSession().createQuery(hql.toString());
			qo.setParameter("username", username);
			qo.setParameter("state", IState.ACTIVE);
			qo.setMaxResults(1);

			Object[] userSession = (Object[])qo.uniqueResult();

			if (userSession != null) {
				CurrentSession currentSession = loadCurrentSession(userSession);
				hql = null;
				hql = new StringBuilder();
				qo = null;

				hql.append(" SELECT ur.id AS id, ");
				hql.append(" ur.email AS email, ");
				hql.append(" ur.idRole AS idRole, ");
				hql.append(" ur.idSystemUser AS idSystemUser, ");
				hql.append(" r.name AS roleName ");
				hql.append(" FROM UserRole ur, ");
				hql.append(" Role r ");
				hql.append(" WHERE r.id = ur.idRole ");
				hql.append(" AND ur.idSystemUser = :idSystemUser ");

				qo = getSession().createQuery(hql.toString()).setResultTransformer(Transformers.aliasToBean(UserRole.class));
				qo.setParameter("idSystemUser", currentSession.getIdSystemUser());

				currentSession.setRoleList(qo.list());
				return currentSession;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			hql = null;
			qo = null;
		}
	}

	private CurrentSession loadCurrentSession(Object[]  userSession) {
		CurrentSession session =new CurrentSession();
		session.setIdSystemUser((Long)userSession[0]);
		session.setIdDocumentType((Long)userSession[1]);
		session.setName(userSession[2].toString());
		session.setLastName(userSession[3].toString());
		session.setDocumentNumber(userSession[4].toString());
		return session;
	}
}
