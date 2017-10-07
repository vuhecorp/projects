package com.hersa.sample.project.bom;




import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.hersa.sample.project.dao.user.User;

@ManagedBean
@ViewScoped
public class AuthenticationManager {
	
	private final int MAX_TRIES = 4;
	private final int LOCKOUT_DURATION = (60 *24); //lockout duration.
	private final int MAX_TRIAL_PERIOD_MIN = 60; //minutes after which the failed attempts resets to 0;
	public  int totalTries;
	UserManager um = new UserManager();
	
	public AuthenticationManager(){
		totalTries = 0;
	}
	public User authenticateUser(String email, String Password){
		User user = null;
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
			try {
				user = um.getUserByUsername(email);
			} catch (Exception e) {;;}
			if (user != null) {
				//set user login history
				Date lastFailedDate = null;
				Date firstFailedDate = null;
				Date lockedOnDate = null;
				int failedAttempts = -1;
				long minSinceLastFailed = -1;
				long minSinceLastLocked = -1;
				long minSinceFirstFailed = -1;
				int locked = user.getLocked();
				try {
					failedAttempts = user.getFailedAttempts();
					lastFailedDate = user.getLastFailed();
					firstFailedDate = user.getFirstFailed();
					lockedOnDate = user.getLockedOn();
					minSinceLastFailed = ((now.getTime() - lastFailedDate.getTime())/1000) /60;
				    minSinceLastLocked = ((now.getTime() - lockedOnDate.getTime())/1000) /60;
				    minSinceFirstFailed = ((now.getTime() - firstFailedDate.getTime())/1000) /60;
				} catch (Exception e) {
					;;
				}
				
				//check if the lockout period has expired, if true, unlock user.
				if (locked == 1 && minSinceLastLocked >= 
						LOCKOUT_DURATION && 
							minSinceLastFailed != -1) {
					try {
						resetUser(email);
						System.out.println("Elapsed time since lock-out exceeds " + LOCKOUT_DURATION +" minutes. user unlocked.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// resets failed attempts after a predefined period of time.
				if (minSinceFirstFailed > MAX_TRIAL_PERIOD_MIN) {
					//user gets 4 attempts on the hour.
					totalTries = 0;
					user.setFailedAttempts(totalTries);
					um.updateUser(user);
				}
				int userLocked = user.getLocked();
				//if the user is not locked, proceed with authentication.
				if (userLocked != 1) {
					//if the user has been recently unlocked, set attempts to 0.
					//this allows the user to keep the same session.
					if (user.getRecentUnlock() == 1) {
						totalTries = 0;
						user.setRecentUnlock(0);
						um.updateUser(user);
					}
					if (totalTries < MAX_TRIES) {
						if (totalTries > 2) {
							int remainingTries = MAX_TRIES - totalTries;
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "You have " + remainingTries + " remaining."));
						}
						if (user.getPassword().equals(Password)) {
							//user authenticated
							FacesContext.getCurrentInstance()
							.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Success!"));
							HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
							session.setAttribute("User", user);
							totalTries = 0;
							user.setFailedAttempts(0);
							um.updateUser(user);
							return user;
						}else{
							//authentication failed.
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Invalid Credentials."));
							totalTries++;
							if (totalTries == 1) {
								user.setFirstFailed(currentTimeStamp);
							}
							user.setFailedAttempts(totalTries);
							user.setLastFailed(currentTimeStamp);
							um.updateUser(user);
						}
					}else{
						//lock user if attempts threshold is reached.
						user.setLocked(1);
						user.setLockedOn(currentTimeStamp);
						um.updateUser(user);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "You have "
								+ "reached the max number of attempts. Your account has been locked out."));
						return null;
					}
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Your account "
							+ "is locked. Please contact an administrator."));
					return null;
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "This email does not exist."));
				return null;
			}
		return null;
	}
	public void resetUser(String email) throws Exception{
		User user = um.getUserByUsername(email);
		user.setLocked(0);
		user.setRecentUnlock(1);
		user.setFailedAttempts(0);
		um.updateUser(user);
	}
}
