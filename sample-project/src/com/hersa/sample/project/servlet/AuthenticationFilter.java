package com.hersa.sample.project.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hersa.sample.project.dao.user.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
	private ServletContext context;
	private User sessionUser;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String root = req.getContextPath();
			String uri = req.getRequestURI();
			boolean loggedIn = true;
			try {
				sessionUser = (User) req.getSession().getAttribute("User");
				if (sessionUser.getEmail().isEmpty()) {
					loggedIn = false;
				}
			} catch (Exception e) {
				loggedIn = false;
			}
			
			if (uri.contains("/private/")) {
				if (loggedIn) {
					if (uri.contains("/sysadmin/")){
						if (!sessionUser.getRole().equalsIgnoreCase("sysadmin")) {
							this.context.log("Unauthorized access request");
								res.sendRedirect(root + "/sysaccess.xhtml");}}
						if (uri.contains("/admin/")){
							if (sessionUser.getRole().equalsIgnoreCase("sysadmin")) {
								
							}else if (!sessionUser.getRole().equalsIgnoreCase("admin")) {
								this.context.log("Unauthorized access request");
								res.sendRedirect(root + "/private/user/userWelcome.xhtml");}}
				}else{
					this.context.log("Unauthorized access request");
					res.sendRedirect(root + "/access.xhtml");
				}
			}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	
	}

}
