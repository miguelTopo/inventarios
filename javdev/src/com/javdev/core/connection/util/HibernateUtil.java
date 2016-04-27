package com.javdev.core.connection.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.common.base.Optional;

public class HibernateUtil {

	private static Optional<SessionFactory> sessionFactory = Optional.absent();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory.isPresent()) {
			return sessionFactory.get();
		}
		sessionFactory = Optional.fromNullable(buildSessionFactory());
		return sessionFactory.get();
	}

	public static void shutdown() {
		// close chaches and connection pools
		getSessionFactory().close();
	}

}
