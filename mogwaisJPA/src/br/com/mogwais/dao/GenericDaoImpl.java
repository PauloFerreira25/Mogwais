package br.com.mogwais.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.starssource.common.utils.log.Logger;

public abstract class GenericDaoImpl<T, entityManagerFactoryName> implements
		GenericDao<T, String> {
	private Logger log = new Logger();

	protected EntityManager em;
	private EntityManagerFactory emf;
	private Class<T> type;

	@Override
	public void setEntityManager(String entityManagerFactoryName) {
		// TODO Auto-generated method stub

	}

	public EntityManager getEntityManager(String entityManagerFactoryName) {
		log.setLogger("mogwaisJPA", "GenericDaoImpl", "getEntityManager");
		log.debug("Get EntityManagerFactory: " + entityManagerFactoryName);

		try {
			if (emf == null) {
				emf = getEntityManagerFactory(entityManagerFactoryName);
			}
			em = emf.createEntityManager();
			return em;
		} catch (Exception e) {
			log.error("Error ", e);
		}
		return null;
	}

	private EntityManagerFactory getEntityManagerFactory(
			String entityManagerFactoryName) {
		log.setLogger("mogwaisJPA", "GenericDaoImpl", "getEntityManager");
		log.debug("Get EntityManagerFactory: " + entityManagerFactoryName);
		try {
			InitialContext ctx = new InitialContext();
			emf = (EntityManagerFactory) ctx.lookup(entityManagerFactoryName);
			return emf;
		} catch (Exception e) {
			log.error("Error ", e);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public long countAll(final Map<String, Object> params) {

		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");

		queryString.append(type.getSimpleName()).append(" o ");
		// queryString.append(this.getQueryClauses(params, null));

		final Query query = this.em.createQuery(queryString.toString());

		return (Long) query.getSingleResult();

	}

	@Override
	public T create(final T t) {
		this.em.persist(t);
		return t;
	}

	@Override
	public void delete(final Object id) {
		this.em.remove(this.em.getReference(type, id));
	}

	@Override
	public T find(final Object id) {
		return (T) this.em.find(type, id);
	}

	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}
}
