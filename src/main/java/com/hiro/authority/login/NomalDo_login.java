package com.hiro.authority.login;


/**
 * @author ZHJ
 * 用户的操作
 */
public interface NomalDo_login {
	
	void ConsuQuery();	//消费查询
	void showBill();  //查看账单
	void oderFood();  //点菜
	void checkout();  //结账
	void rePass();		//修改积分

	void getRoom(); //开包间
	
	void seeRoom();  //查看包间状态

}
