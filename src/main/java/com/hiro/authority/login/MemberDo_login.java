package com.hiro.authority.login;

import com.hiro.hotel.entity.login.Member_login;

/**
 * @author  ZHJ
 * �Ի�Ա���е�һЩ����
 */
public interface MemberDo_login {
	
	void create_login();	//ע���Ա
	void seeMember_login();    //�鿴��Ա��Ϣ
	void delMember_login();    //ɾ����Ա
	Member_login searchMember();	//���һ�Ա
	void modifyIntegral();		//�޸Ļ���

}
