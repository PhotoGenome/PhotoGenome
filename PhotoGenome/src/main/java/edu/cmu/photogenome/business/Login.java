package edu.cmu.photogenome.business;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts2.json.JSONWriter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cedarsoftware.util.io.JsonWriter;
import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.dao.UserDao;
import edu.cmu.photogenome.dao.UserDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.User;
import edu.cmu.photogenome.util.HashUtil;
import edu.cmu.photogenome.util.HibernateUtil;

public class Login {
	private UserDao userDao;
	public Login(){
    	userDao = new UserDaoImpl();
    }
    
	/**
	 * Constructor that also sets the Hibernate session to be used
	 * 
	 * @param session	Hibernate session to use when calling DAOs
	 */
	public Login(Session session) {
		this();
		setSession(session);
	}
	
	/**
	 * Set the Hibernate session to use when calling DAOs
	 * 
	 * @param session
	 */
	public void setSession(Session session) {
		userDao.setSession(session);
		}
	
	public User getUser(String property, Object value) {
		return userDao.findAllByCriteria(property, value).get(0);
	}
	public User verifyUserPassword(String username, String password){
    	User user=null;
    	try {
	 	 user = getUser("userEmailId",username);
	 	 String hashEnteredPassword;
    	if(user != null) {
    			hashEnteredPassword=HashUtil.getHash(password);
    			System.out.println(hashEnteredPassword+"--"+user.getUserPassword());
				if(hashEnteredPassword.equals(user.getUserPassword())){
					return user;
				}
				else {
					return null;
				}
			} else {
	    		return null;
	    	}
    	}
			catch(Exception e) {
				return null;
			}
    	
    }
   
}