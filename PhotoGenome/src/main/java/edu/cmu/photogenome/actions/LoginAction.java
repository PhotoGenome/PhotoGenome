package edu.cmu.photogenome.actions;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.Login;
import edu.cmu.photogenome.dao.UserDao;
import edu.cmu.photogenome.dao.UserDaoImpl;
import edu.cmu.photogenome.domain.User;
import edu.cmu.photogenome.util.HibernateUtil;

public class LoginAction extends ActionSupport {
	final Logger log = LoggerFactory.getLogger(LoginAction.class);
	
	private int userId;
	
	private String username;
    private String password;
    private String id;
    private String jsonFindUser;
    private LinkedHashMap<String, Object> jsonLoginData = new LinkedHashMap<String, Object>();
    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LinkedHashMap<String, Object> getJsonLoginData() {
		return jsonLoginData;
	}
	public void setJsonLoginData(LinkedHashMap<String, Object> jsonLoginData) {
		this.jsonLoginData = jsonLoginData;
	}
	public LinkedHashMap<String, Object> getJsonRegisterData() {
		return jsonRegisterData;
	}
	public void setJsonRegisterData(LinkedHashMap<String, Object> jsonRegisterData) {
		this.jsonRegisterData = jsonRegisterData;
	}

	private LinkedHashMap<String, Object> jsonRegisterData = new LinkedHashMap<String, Object>();
    private final String jsonKey = getText("json.key");
    private Login login= new Login();
    
    
	private UserDao userDao = new UserDaoImpl();

	private String firstname;

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private String lastname;

	private String email;

	private HashMap<String, Object> jsonUserData = new LinkedHashMap<String, Object>();
    
	  public HashMap<String, Object> getJsonUserData() {
		return jsonUserData;
	}
	public void setJsonUserData(HashMap<String, Object> jsonUserData) {
		this.jsonUserData = jsonUserData;
	}
	public String verifyUserPassword(){
	    	Session session = HibernateUtil.getSessionFactory().openSession();
			login.setSession(session);
			HibernateUtil.beginTransaction(session);
			try {
			
	    	User user = login.verifyUserPassword(username,password);
	    	
	    	if(user != null) {
	    		jsonLoginData.put(jsonKey, user);
						HibernateUtil.commitTransaction(session);
						return SUCCESS;
					}
					else {
						jsonLoginData.put(jsonKey, "NotAUser");
						addActionError(getText("error.login"));
				    	HibernateUtil.rollbackTransaction(session);
						return SUCCESS;
					}
				
	    	}
				catch(Exception e) {
					log.warn(e.getMessage(), e);
					HibernateUtil.rollbackTransaction(session);
					return SUCCESS;
				}
	    	
	    }
	  public String  verifyUserDetailsAndRegister(){
	    	Session session = HibernateUtil.getSessionFactory().openSession();
			login.setSession(session);
			HibernateUtil.beginTransaction(session);
			try {
			System.out.println(firstname+","+ lastname+","+ email+","+ password);
	    	User user = login.verifyUserDetailsAndRegister(firstname, lastname, email, password);
	    	
	    	if(user != null) {
	    		jsonRegisterData.put(jsonKey, user);
						HibernateUtil.commitTransaction(session);
						System.out.println(jsonRegisterData);		
					    
						return SUCCESS;
					}
					else {
						jsonRegisterData.put(jsonKey, "NotRegistered");
						addActionError(getText("error.login"));
				    	HibernateUtil.rollbackTransaction(session);
				    	System.out.println(jsonRegisterData);		
					    
				    	return SUCCESS;
					}
			}
				catch(Exception e) {
					log.warn(e.getMessage(), e);
					HibernateUtil.rollbackTransaction(session);
					return SUCCESS;
				}
	    	
	    }
	  public String  getUserByUserId(){
	    	Session session = HibernateUtil.getSessionFactory().openSession();
			login.setSession(session);
			HibernateUtil.beginTransaction(session);
			try {
			User user = login.getUserByUserId(userId);
				
	    	if(user != null) {
	    		jsonUserData.put(jsonKey, user);
						HibernateUtil.commitTransaction(session);
						System.out.println(jsonUserData.toString());		
					    
						return SUCCESS;
					}
					else {
						jsonUserData.put(jsonKey, "NotAUser");
						addActionError(getText("error.login"));
				    	HibernateUtil.rollbackTransaction(session);
				    	System.out.println(jsonRegisterData);		
					    
				    	return SUCCESS;
					}
			}
				catch(Exception e) {
					log.warn(e.getMessage(), e);
					HibernateUtil.rollbackTransaction(session);
					return SUCCESS;
				}
	    	
	    }
	  
  
	 	
	public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}