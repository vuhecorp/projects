package com.hersa.sample.project.session;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.hersa.sample.project.dao.user.User;

public class SessionState implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static HttpSession session;
	
	public SessionState() {
		// TODO Auto-generated constructor stub
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpSession getSession(){
		if (session != null) {
			return session;
		}else{
			return session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext()
					.getSession(true);
		}
	}
	
	public static void invalidateSession(){
		session.invalidate();
	}
	public static void setSessionUser(User user){
		if (session != null) {
			session.setAttribute("User", user);
		}else{
			getSession();
			session.setAttribute("User", user);
		}
	}
	public static User getSessionUser(){
		return (User) session.getAttribute("User");
	}
}
