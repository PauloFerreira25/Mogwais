package br.com.mogwais.ejb.gizmo.account;

import javax.ejb.EJB;

import br.com.mogwais.dao.gizmo.account.UserDao;

public class userEJB {
	
	@EJB private UserDao userDao;
	
}
