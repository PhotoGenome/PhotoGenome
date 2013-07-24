package edu.cmu.photogenome.actions;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts2.json.JSONWriter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cedarsoftware.util.io.JsonWriter;
import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.Login;
import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.dao.UserDao;
import edu.cmu.photogenome.dao.UserDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.User;
import edu.cmu.photogenome.util.HashUtil;
import edu.cmu.photogenome.util.HibernateUtil;

public class LoginAction extends ActionSupport {
	final Logger log = LoggerFactory.getLogger(LoginAction.class);
	
    private String username;
    private String password;
    private String id;
    private String jsonFindUser;
    private LinkedHashMap<String, Object> jsonData = new LinkedHashMap<String, Object>();
    private final String jsonKey = getText("json.key");
    private Login login= new Login();
    
    
	private UserDao userDao = new UserDaoImpl();
    
	  public String verifyUserPassword(){
	    	Session session = HibernateUtil.getSessionFactory().openSession();
			login.setSession(session);
			HibernateUtil.beginTransaction(session);
			try {
			
	    	User user = login.verifyUserPassword(username,password);
	    	
	    	if(user != null) {
	    				jsonData.put(jsonKey, user);
						HibernateUtil.commitTransaction(session);
						return SUCCESS;
					}
					else {
						addActionError(getText("error.login"));
				    	HibernateUtil.rollbackTransaction(session);
						return ERROR;
					}
				
	    	}
				catch(Exception e) {
					log.warn(e.getMessage(), e);
					HibernateUtil.rollbackTransaction(session);
					return ERROR;
				}
	    	
	    }
	public LinkedHashMap<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
		this.jsonData = jsonData;
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