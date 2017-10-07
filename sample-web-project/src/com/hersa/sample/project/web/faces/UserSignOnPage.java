package com.hersa.sample.project.web.faces;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.hersa.sample.project.bom.AuthenticationManager;
import com.hersa.sample.project.dao.user.User;
import com.mysql.jdbc.StringUtils;


@ManagedBean(name="SignOn")
@SessionScoped
public class UserSignOnPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private User user;
	private AuthenticationManager am = new AuthenticationManager();

	public UserSignOnPage(){
		user = new User();
	
	}
	public String doSignOn(){
		boolean authenticated = false;
		if (!StringUtils.isEmptyOrWhitespaceOnly(email) && !StringUtils.isEmptyOrWhitespaceOnly(password)) {
			user = am.authenticateUser(email, password);
			if (user != null) {
				authenticated = true;
				email = new String();
				password = new String();
			}
		}else{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Username "
			 		+ "and password must be specified."));
		}
		if (authenticated) {
			if (user.getRole().equalsIgnoreCase("admin")) {
				return "/private/admin/adminWelcome?faces-redirect=true";
			}else if(user.getRole().equalsIgnoreCase("sysadmin")){
				return "/private/sysadmin/sysadminWelcome?faces-redirect=true";
			}else{
				return "/private/user/userWelcome?faces-redirect=true";
			}
		}else{
			return "";
		}
	}
	public void resetUser(){
		try {
			am.resetUser(email);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "User has been reset"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User reset failed."));
		}
	}
	public String doSignOff(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return"/signon/signon?faces-redirect=true";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
