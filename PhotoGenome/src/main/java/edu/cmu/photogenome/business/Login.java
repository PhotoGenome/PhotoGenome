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
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.User;
import edu.cmu.photogenome.util.HashUtil;
import edu.cmu.photogenome.util.HibernateUtil;

public class Login {
	private UserDao userDao;
	final Logger log = LoggerFactory.getLogger(Login.class);
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
		List<User> users= userDao.findAllByCriteria(property, value);
		if(!users.isEmpty()){
			return userDao.findAllByCriteria(property, value).get(0);
			}
		else{
		return null;
		}
	}
	public User verifyUserPassword(String username, String password){
    	User user=null;
    	try {
	 	 user = getUser("userEmailId",username);
	 	 String hashEnteredPassword;
    	if(user != null) {
    			hashEnteredPassword=HashUtil.getHash(password);
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
 
	public User verifyUserDetailsAndRegister(String firstname,String lastname,String email, String password){
    	User user=null;
    	try {
    		System.out.println("Business");
   	 	 
    		user = getUser("userEmailId",email);
	 	 System.out.println("User"+user);
	 	 String hashEnteredPassword;
    	if(user == null) {
    			hashEnteredPassword=HashUtil.getHash(password);
    			user = new User();

    			user.setUserFirstName(firstname);
    			user.setUserLastName(lastname);
    			user.setUserEmailId(email);
    			user.setUserPassword(hashEnteredPassword);
    			user.setUserTimestamp(new Date());

    			if(userDao.save(user)) {
    				log.debug("User saved. ", user.getUserId());	
    				return user;
    			}
    			else {
    				log.error("Failed to save user ", user.getUserId());
    				return null;
    			}
    	}
    			else {
    				return null;
    		    				
    			}
    	}
			catch(Exception e) {
				return null;
			}
    	
    }
 
}