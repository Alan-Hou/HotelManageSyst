package com.hiro.hotel.entity.login;

import com.hiro.hotel.biz.login.MemberManage_login;

/**
 * @author XX
 * 会员类
 */
public class Member_login  {
	
	private String number; //会员号
	private String MemberName;//会员姓名
	private int integral; // 会员积分
	private String password;  //登录密码
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	
	
	public Member_login(){
		
	}
	
	public Member_login(String memberName, String password){
		this.MemberName=memberName;
		this.password=password;
	}
	
	public Member_login(String number, String memberName, int integral, String password){
		this.number=number;
		this.MemberName=memberName;
		this.integral=integral;
		this.password=password;
	}
	

}
