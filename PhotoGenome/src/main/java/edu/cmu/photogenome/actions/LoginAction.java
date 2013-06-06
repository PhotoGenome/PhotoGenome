package edu.cmu.photogenome.actions;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.dao.UserDao;
import edu.cmu.photogenome.dao.UserDaoImpl;
import edu.cmu.photogenome.domain.User;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    private String id;
 
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
    	User user = userDao.findById(Integer.parseInt(this.username));
    	user.setUserFirstName(this.password);
    	userDao.update(user);
    	
    	return SUCCESS;
    }
    
    public String findUser() {
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