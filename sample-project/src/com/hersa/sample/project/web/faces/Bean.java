package com.hersa.sample.project.web.faces;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.hersa.sample.project.dao.user.User;


public class Bean {
	private User sessionUser;
	public Bean() {
		// TODO Auto-generated constructor stub
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.sessionUser = (User) session.getAttribute("User");
	}
	public User getSessionUser() {
		return sessionUser;
	}
	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}

}
