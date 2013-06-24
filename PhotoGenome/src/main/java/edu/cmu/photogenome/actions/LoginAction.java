package edu.cmu.photogenome.actions;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.struts2.json.JSONWriter;
import org.hibernate.Session;

import com.cedarsoftware.util.io.JsonWriter;
import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.dao.UserDao;
import edu.cmu.photogenome.dao.UserDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.User;
import edu.cmu.photogenome.util.HibernateUtil;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    private String id;
    private String jsonFindUser;
    private LinkedHashMap<String, Object> jsonData = new LinkedHashMap<String, Object>();
    
    public String getJsonFindUser() {
		return jsonFindUser;
	}

	public void setJsonFindUser(String jsonFindUser) {
		this.jsonFindUser = jsonFindUser;
	}

	private UserDao userDao = new UserDaoImpl();
    
    public String execute() {
 
        if (this.username.equals("admin") 
                && this.password.equals("admin123")) {
            return "success";
        } else {
            addActionError(getText("error.login"));
            return "error";
        }
    }
 
    public String addUser() {
    	User newUser = new User(new Date());
    	newUser.setUserFirstName(this.username);
    	userDao.save(newUser);
    	
    	id = String.valueOf(newUser.getUserId());
    	username = id;
    	return SUCCESS;
    }
    
    public String removeUser() {
    	List<User> list = userDao.findAll();
    	for(User user : list) {
    		if(user.getUserFirstName().equals(this.username))
    			userDao.delete(user);
    	}
    	
    	return SUCCESS;
    }
    
    public String updateUser() {
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	HibernateUtil.beginTransaction(session);
    	userDao.setSession(session);
    	//HibernateUtil.beginTransaction(userDao.getSession());
    	User user = userDao.findById(Integer.parseInt(this.username));
    	System.out.println("FIRST NAME = " + user.getUserFirstName());
    	user.setUserFirstName(this.password);
    	if(userDao.update(user))
    		HibernateUtil.commitTransaction(userDao.getSession());
    	else
    		HibernateUtil.rollbackTransaction(userDao.getSession());
    	
    	return SUCCESS;
    }
    
    public String findUser() {
    	Photo photo = new Photo();
    	//photo.setPhotoDesc("my description");
    	//photo.setPhotoId(10);
    	//Photo photo2 = new Photo();
    	//photo2.setPhotoDesc("my description 2");
    	//photo2.setPhotoId(20);
    	//ArrayList<Photo> list = new ArrayList<Photo>();
    	//list.add(photo);
    	//list.add(photo2);
    	ViewInformation viewInformation = new ViewInformation();
    	photo = viewInformation.getPhoto(1);
    	
    	 try {
			jsonFindUser = JsonWriter.objectToJson(photo);
			JSONWriter writer = new JSONWriter();
			System.out.println("STRUTS WRITER " + writer.write(photo));
			System.out.println("JSON*******   " + jsonFindUser);
			jsonData.put("json key", jsonFindUser);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	User user = userDao.findByFirstName(this.username);
    	if(user != null) {
    		id = String.valueOf(user.getUserId());
    		return SUCCESS;
    	}
    	else {
    		addActionError(getText("error.login"));
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