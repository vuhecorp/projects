package com.hersa.sample.project.dao.userprofile;

import java.io.Serializable;

public class UserProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3099596366764214287L;
	private Long ROWID;
	private Long USERID;
	private String PHONE;
	private String ADDRESS;
	private String ADDRESS2;
	private String CITY;
	private String STATE;
	private String ZIP;

	public Long getROWID(){
		return this.ROWID;
	}

	public Long getUSERID(){
		return this.USERID;
	}

	public String getPHONE(){
		return this.PHONE;
	}

	public String getADDRESS(){
		return this.ADDRESS;
	}

	public String getADDRESS2(){
		return this.ADDRESS2;
	}

	public String getCITY(){
		return this.CITY;
	}

	public String getSTATE(){
		return this.STATE;
	}

	public String getZIP(){
		return this.ZIP;
	}


	public void setROWID(Long ROWID){
		this.ROWID = ROWID;
	}

	public void setUSERID(Long USERID){
		this.USERID = USERID;
	}

	public void setPHONE(String PHONE){
		this.PHONE = PHONE;
	}

	public void setADDRESS(String ADDRESS){
		this.ADDRESS = ADDRESS;
	}

	public void setADDRESS2(String ADDRESS2){
		this.ADDRESS2 = ADDRESS2;
	}

	public void setCITY(String CITY){
		this.CITY = CITY;
	}

	public void setSTATE(String STATE){
		this.STATE = STATE;
	}

	public void setZIP(String ZIP){
		this.ZIP = ZIP;
	}

}