package edu.cmu.photogenome.dao;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.User;

public class UserDaoImpl extends GenericAbstractDaoImpl<User, Integer> implements UserDao {

	public User findByFirstName(String firstName) {
		User user = null;
		try {
			session.beginTransaction();
			user = (User) session.createCriteria(type).add(Restrictions.eq("userFirstName", firstName)).uniqueResult();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return user;
	}
}
