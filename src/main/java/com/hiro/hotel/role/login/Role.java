package com.hiro.hotel.role.login;

/**
 * author ZHJ
 * ��½���û��Ľ�ɫ��
 */
public class Role {
	
	private String description;// ��ɫ��

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
