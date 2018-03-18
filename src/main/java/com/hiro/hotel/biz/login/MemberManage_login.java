package com.hiro.hotel.biz.login;

import java.util.ArrayList;

import com.hiro.hotel.entity.login.Member_login;
import com.hiro.Jdbc.JmemberManage_login;

/**
 * @author XX
 * 会员管理
 */
public class MemberManage_login {
	public static ArrayList<Member_login> members = new ArrayList<Member_login>();
	//初始化
	public static void IniMembers(){


		members= JmemberManage_login.getMembers();
	}
	
	public void addMember_login(Member_login member){
		members.add(member);
	}
	
	public static Member_login findMemberById_login(String memberId){
		for(int i=1; i<members.size(); i++){
			if(members.get(i).getNumber().equals(memberId)){
				return members.get(i);
			}
		}
		return null;
	}


public static Member_login findMemberByName_login(String name)
{
	if(JmemberManage_login.searchNmaeRet(name)==null)
	{
		System.out.println("该会员不存在");
		return null;
	}
	else
	{
		return JmemberManage_login.searchRet(name);
	}

}
	//删除会员
	public void delMember_login(String memberId){
		Member_login member = findMemberById_login(memberId);
		members.remove(member);
	}
	//显示会员信息
	public void showMember(){
		MemberManage_login manage_login=new MemberManage_login();//从数据库更新会员信息
		manage_login.IniMembers();
		System.out.println("**********会员信息表*************");
		System.out.println("会员号\t会员姓名\t会员积分");
		for(int i=1; i<members.size(); i++){
			System.out.println(members.get(i).getNumber()+"\t"+members.get(i).getMemberName()+"\t"+members.get(i).getIntegral());
		}
		System.out.println("\n\n");
	}

}
