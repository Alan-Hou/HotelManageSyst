package com.hiro.authority.login;

import com.hiro.hotel.entity.login.Member_login;

/**
 * @author  ZHJ
 * 对会员进行的一些操作
 */
public interface MemberDo_login {
	
	void create_login();	//注册会员
	void seeMember_login();    //查看会员信息
	void delMember_login();    //删除会员
	Member_login searchMember();	//查找会员
	void modifyIntegral();		//修改积分

}
