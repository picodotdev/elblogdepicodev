package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.hibernate.HibernateSessionSource;
import org.apache.tapestry5.ioc.services.PerthreadManager;
import org.hibernate.Session;

public class HibernateSessionManagerImpl implements HibernateSessionManager {

	private HibernateSessionSource source;
	private PerthreadManager manager;

	private Session session;

	public HibernateSessionManagerImpl(HibernateSessionSource source, PerthreadManager manager) {
		this.source = source;
		this.manager = manager;
	}

	public void abort() {
		session.getTransaction().rollback();
	}

	public void commit() {
		session.getTransaction().commit();
	}

	public Session getSession() {
		if (session == null) {
			session = source.create();

			manager.addThreadCleanupCallback(new Runnable() {
				@Override
				public void run() {
					cleanup();
				}
			});
		}
		return session;
	}

	private void cleanup() {
		if (session == null) {
			return;
		}
		session.close();
	}
}