package br.com.mogwais.dao;

import java.util.Map;

@SuppressWarnings("hiding")
public interface GenericDao<T, String> {

	
	void setEntityManager(String entityManagerFactoryName);

	/**
	 * Method that returns the number of entries from a table that meet some
	 * criteria (where clause params)
	 *
	 * @param params
	 *            sql parameters
	 * @return the number of records meeting the criteria
	 */
	long countAll(Map<String, Object> params);

	T create(T t);

	void delete(Object id);

	T find(Object id);

	T update(T t);
}
