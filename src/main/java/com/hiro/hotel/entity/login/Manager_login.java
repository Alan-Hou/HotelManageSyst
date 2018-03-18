package com.hiro.hotel.entity.login;

/**
 * @author ZHJ
 * 管理员类
 */
public class Manager_login {
	
	private String mgrId; //管理员姓名
	private String mgrPassword;  //密码
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	public String getMgrPassword() {
		return mgrPassword;
	}
	public void setMgrPassword(String mgrPassword) {
		this.mgrPassword = mgrPassword;
	}

}
