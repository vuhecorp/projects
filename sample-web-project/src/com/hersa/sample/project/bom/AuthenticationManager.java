package com.hersa.sample.project.bom;




import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;


import com.hersa.sample.project.dao.user.User;
import com.hersa.sample.project.dao.usersignon.UserSignOn;

public class AuthenticationManager {
	
	private final int MAX_TRIES = 4;
	private final int LOCKOUT_DURATION = (60 *24); //lockout duration. == (60 *24)
	private final int MAX_TRIAL_PERIOD_MIN = 60; //minutes after which the failed attempts resets to 0; == 60
	public  int totalTries;
	private UserManager um = new UserManager();
	private UserSignOnManager usm = new UserSignOnManager();
	private String previousUser = null;
	
	public AuthenticationManager(){
		totalTries = 0;
	}
	public User authenticateUser(String email, String Password) throws Exception{
		User user = null;
		UserSignOn userSignon = null;
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
			try {
				user = um.getUserByUsername(email);
				userSignon = usm.retrieveUserSignOnByUserId(user.getId());
				
				if (previousUser == null) {
					previousUser = user.getEmail();
				}else {
					if (!previousUser.equals(user.getEmail())) {
						totalTries = 0;
						previousUser = user.getEmail();
					}
				}
			} catch (Exception e) {
				throw e;
			}
			if (user != null && userSignon != null) {
				//set user login history
				Date lastFailedDate = null;
				Date firstFailedDate = null;
				Date lockedOnDate = null;
				int failedAttempts = -1;
				long minSinceLastFailed = -1;
				long minSinceLastLocked = -1;
				long minSinceFirstFailed = -1;
				int locked = userSignon.getLocked();
				try {
					
					failedAttempts = userSignon.getFailedAttempts();
					lastFailedDate = (Date) userSignon.getLastFailed();
					firstFailedDate = (Date) userSignon.getFirstFailed();
					lockedOnDate = userSignon.getLockedOn();
					minSinceLastFailed = ((now.getTime() - lastFailedDate.getTime())/1000) /60;
				    minSinceLastLocked = ((now.getTime() - lockedOnDate.getTime())/1000) /60;
				    minSinceFirstFailed = ((now.getTime() - firstFailedDate.getTime())/1000) /60;
				    
				    try {
						totalTries = failedAttempts;
					} catch (Exception e) {
						// TODO: handle exception
					}
				} catch (Exception e) {
					;;
				}	
				
				//check if the lockout period has expired, if true, unlock user.
				if (locked == 1 && minSinceLastLocked >= 
						LOCKOUT_DURATION && 
							minSinceLastFailed != -1) {
					try {
						resetUser(email);
						user = um.getUserByUsername(email);
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
					userSignon.setFailedAttempts(totalTries);
					usm.updateUserSignOn(userSignon);
				}
				
				int userLocked = userSignon.getLocked();
				
				//if the user is not locked, proceed with authentication.
				if (userLocked != 1) {
					
					//if the user has been recently unlocked, set attempts to 0.
					//this allows the user to keep the same session.
					if (userSignon.getRecentUnlock() == 1) {
						totalTries = 0;
						userSignon.setRecentUnlock(0);
					}
					if (totalTries < MAX_TRIES) {
//						if (totalTries > 2) {
//							int remainingTries = MAX_TRIES - totalTries;
//							FacesContext.getCurrentInstance().addMessage(null, 
//									new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "You have " 
//													+ remainingTries + " remaining."));
//						}
						if (userSignon.getPassword().equals(Password)) {
							
							//user authenticated
							totalTries = 0;
							userSignon.setFailedAttempts(0);
							Date now1 = new Date();
							Timestamp stamp = new Timestamp(now1.getTime());
							userSignon.setLastLogin(stamp);
							usm.updateUserSignOn(userSignon);
							return user;
						}else{
							
							//authentication failed.
							totalTries++;
							currentTimeStamp = new Timestamp(now.getTime());
							
							if (totalTries == 1) {
								userSignon.setFirstFailed(currentTimeStamp);
							}
							
							userSignon.setFailedAttempts(totalTries);
							userSignon.setLastFailed(currentTimeStamp);
							//um.updateUserSignon(user);
							usm.updateUserSignOn(userSignon);
						}
					}else{
						
						//lock user if attempts threshold is reached.
						userSignon.setLocked(1);
						currentTimeStamp = new Timestamp(now.getTime());
						userSignon.setLockedOn(currentTimeStamp);
						usm.updateUserSignOn(userSignon);
						throw new Exception("You have reached the max number of attempts. Your account has been locked out.");
					}
				}else{
					throw new Exception("Your account has been locked out.");
				}
			}else{
				throw new Exception("This email does not exist.");
			}
		return null;
	}
	public void resetUser(String email) throws Exception{
		User user = um.getUserByUsername(email);
		UserSignOn userSignOn = usm.retrieveUserSignOnByUserId(user.getId());
		userSignOn.setLocked(0);
		userSignOn.setRecentUnlock(1);
		userSignOn.setFailedAttempts(0);
		usm.updateUserSignOn(userSignOn);
	}
}
