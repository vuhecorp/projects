package com.hersa.sample.project.startup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.hersa.sample.project.ApplicationContext;
import com.hersa.sample.project.xmlconfig.Client;


@ManagedBean(eager=true) 
@ApplicationScoped
public class ApplicationInitializer{
	
	public ApplicationInitializer() {

	}

	@PostConstruct
	public void init() {
		ApplicationContext.getInstance();
	}

	@PreDestroy
	public void destroy() {
		// Do stuff during webapp's shutdown.
	}
}
