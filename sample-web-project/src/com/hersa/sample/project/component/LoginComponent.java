package com.hersa.sample.project.component;

import java.io.IOException;

import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

public class LoginComponent extends UINamingContainer{

	public LoginComponent() {
		super();
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException{
		String email = (String) getAttributes().get("loginBean");
		
		System.err.println(email + "<------------------");
	}
}
