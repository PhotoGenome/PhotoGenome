package edu.cmu.photogenome.dao;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.User;

public class UserDaoImpl extends GenericAbstractDaoImpl<User, Integer> implements UserDao {

	public User findByFirstName(String firstName) {
		User user = null;
		try {
			user = (User) session.createCriteria(type).add(Restrictions.eq("userFirstName", firstName)).uniqueResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
