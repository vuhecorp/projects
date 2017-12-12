package com.hersa.sample.project.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class StaticMethodUtils {

	public StaticMethodUtils() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void addFacesMessage(Severity severity, String message, String header){
	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity,
				message, header));
	}

}
