package com.hiro.hotel.role.login;

/**
 * author ZHJ
 * 登陆的用户的角色类
 */
public class Role {
	
	private String description;// 角色名

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Role(){
		
	}
	
	public Role(String name){
		this.description=name;
	}

}
