package br.com.mogwais.dao.gizmo.account;

import br.com.mogwais.dao.GenericDaoImpl;
import br.com.mogwais.jpa.gizmo.account.User;

import java.util.List;

import javax.persistence.Query;

public class UserDao extends GenericDaoImpl<User, String> {
	
	public UserDao(String entityManagerFactoryName){
		
	}

	public User loadUserByHashUser(String hashUser) {
		Query query = this.em
				.createQuery("select u FROM User u where u.hashUser= :hashUser");
		query.setParameter("hashUser", hashUser);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

}
