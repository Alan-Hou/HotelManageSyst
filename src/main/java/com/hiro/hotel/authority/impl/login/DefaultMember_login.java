package com.hiro.hotel.authority.impl.login;

import com.hiro.Jdbc.JmemberManage_login;
import com.hiro.authority.login.MemberDo_login;
import com.hiro.hotel.biz.login.MemberManage_login;
import com.hiro.hotel.entity.login.Member_login;

import java.util.Scanner;

/**
 * @author ZHJ
 * 管理员对会员的进行管理操作
 */
public class DefaultMember_login implements MemberDo_login {

    Scanner input = new Scanner(System.in);
    MemberManage_login memberLogin = new MemberManage_login();

    //注册会员
    @Override
    public void create_login() {

        System.out.println("请输入会员姓名:");
        String tempName = input.next();
        System.out.println("请输入会员号:");
        String tempId = input.next();
        while (true) {
            if (tempId.length() <= 13) {
                Member_login tempManager = memberLogin.findMemberById_login(tempId);
                if (tempManager != null) {
                    System.out.println("该会员号已经被注册,注册失败！");
                    return;
                }
                System.out.println("请输入密码:");
                String password = input.next();
                System.out.println("请再次输入密码:");
                String password2 = input.next();
                while (!password.equals(password2)) {
                    System.out.println("两次密码输入不一致，请再次输入!");
                    System.out.println("请输入密码:");
                    password = input.next();
                    System.out.println("请再次输入密码:");
                    password2 = input.next();
                }
                Member_login tempMember = new Member_login(tempId, tempName, 0, password);
                memberLogin.addMember_login(tempMember);
                JmemberManage_login.addMember(tempMember.getNumber(), tempMember.getMemberName(), tempMember.getPassword());
                System.out.println("注册成功!");
                return;
            } else {
                System.out.println("您输入的会员号不合法,请重新输入!");
                System.out.println("请输入会员号:");
                tempId = input.next();
            }
        }
    }

    @Override
    public void seeMember_login() {
        memberLogin.showMember();

    }

    //删除会员
    @Override
    public void delMember_login() {
        System.out.println("请输入要删除的会员号:");
        String memberId = input.next();
        Member_login tempMember = memberLogin.findMemberById_login(memberId);
        if (tempMember != null) {
            memberLogin.delMember_login(memberId);
            JmemberManage_login.delMember(memberId);
            System.out.println("成功删除该会员!");
        } else {
            System.out.println("该会员不存在，删除失败!");
        }


    }

    //查找会员
    @Override
    public Member_login searchMember() {
        System.out.println("请输入要查找的会员号:");
        String memberId = input.next();
        boolean isFind = false;
        isFind = JmemberManage_login.search(memberId);
        Member_login member = JmemberManage_login.searchRet(memberId);
        if (isFind ==true) {
            System.out.println("会员号为" + memberId + "的会员姓名为:" + member.getMemberName() + ",该会员的积分为:" + member.getIntegral());
            return member;
        } else {
            System.out.println("该会员不存在！");
            return null;
        }

    }

    /**
     * 直接修改会员积分
     */
    @Override
    public void modifyIntegral() {
        System.out.println("请输入要修改会员积分的会员号:");
        String tempId = input.next();
        Member_login member = memberLogin.findMemberById_login(tempId);
        if (JmemberManage_login.search(tempId) == false) {
            System.out.println("该会员不存在，修改失败!");
        } else {
            System.out.println("请输入要修改的积分数:");
            int tempIntegral = input.nextInt();
            member.setIntegral(tempIntegral);
            JmemberManage_login.modifyIntegral(tempId, tempIntegral);
            System.out.println("修改成功!");
        }

    }

    /**
     * 消费增加会员积分
     */

    public void Integral(String name, double sum) {
        Member_login member = null;
        member = JmemberManage_login.searchNmaeRet(name);
        if(member!=null)
        {
            member.setIntegral(member.getIntegral() + (int )sum / 10);
            JmemberManage_login.modifyIntegral(member.getNumber(), member.getIntegral());
        }

    }
}
