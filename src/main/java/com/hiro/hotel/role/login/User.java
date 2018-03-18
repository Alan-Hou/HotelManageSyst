package com.hiro.hotel.role.login;

import com.hiro.hotel.biz.login.MemberManage_login;

/**
 * author ZHJ
 * 用来判断登录用户的角色
 */
public class User {
	
	private Role role = null;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	//登录是否正确，密码是否匹配
	public boolean login(String username, String password) {
		for(int i=0; i<MemberManage_login.members.size(); i++){
			if(MemberManage_login.members.get(i).getMemberName().equals(username)
					&&MemberManage_login.members.get(i).getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}

	
	

}
