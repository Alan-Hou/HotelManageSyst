package com.hiro.hotel.authority.impl.login;

import com.hiro.Jdbc.JmemberManage_login;
import com.hiro.authority.login.MemberDo_login;
import com.hiro.hotel.biz.login.MemberManage_login;
import com.hiro.hotel.entity.login.Member_login;

import java.util.Scanner;

/**
 * @author ZHJ
 * ����Ա�Ի�Ա�Ľ��й������
 */
public class DefaultMember_login implements MemberDo_login {

    Scanner input = new Scanner(System.in);
    MemberManage_login memberLogin = new MemberManage_login();

    //ע���Ա
    @Override
    public void create_login() {

        System.out.println("�������Ա����:");
        String tempName = input.next();
        System.out.println("�������Ա��:");
        String tempId = input.next();
        while (true) {
            if (tempId.length() <= 13) {
                Member_login tempManager = memberLogin.findMemberById_login(tempId);
                if (tempManager != null) {
                    System.out.println("�û�Ա���Ѿ���ע��,ע��ʧ�ܣ�");
                    return;
                }
                System.out.println("����������:");
                String password = input.next();
                System.out.println("���ٴ���������:");
                String password2 = input.next();
                while (!password.equals(password2)) {
                    System.out.println("�����������벻һ�£����ٴ�����!");
                    System.out.println("����������:");
                    password = input.next();
                    System.out.println("���ٴ���������:");
                    password2 = input.next();
                }
                Member_login tempMember = new Member_login(tempId, tempName, 0, password);
                memberLogin.addMember_login(tempMember);
                JmemberManage_login.addMember(tempMember.getNumber(), tempMember.getMemberName(), tempMember.getPassword());
                System.out.println("ע��ɹ�!");
                return;
            } else {
                System.out.println("������Ļ�Ա�Ų��Ϸ�,����������!");
                System.out.println("�������Ա��:");
                tempId = input.next();
            }
        }
    }

    @Override
    public void seeMember_login() {
        memberLogin.showMember();

    }

    //ɾ����Ա
    @Override
    public void delMember_login() {
        System.out.println("������Ҫɾ���Ļ�Ա��:");
        String memberId = input.next();
        Member_login tempMember = memberLogin.findMemberById_login(memberId);
        if (tempMember != null) {
            memberLogin.delMember_login(memberId);
            JmemberManage_login.delMember(memberId);
            System.out.println("�ɹ�ɾ���û�Ա!");
        } else {
            System.out.println("�û�Ա�����ڣ�ɾ��ʧ��!");
        }


    }

    //���һ�Ա
    @Override
    public Member_login searchMember() {
        System.out.println("������Ҫ���ҵĻ�Ա��:");
        String memberId = input.next();
        boolean isFind = false;
        isFind = JmemberManage_login.search(memberId);
        Member_login member = JmemberManage_login.searchRet(memberId);
        if (isFind ==true) {
            System.out.println("��Ա��Ϊ" + memberId + "�Ļ�Ա����Ϊ:" + member.getMemberName() + ",�û�Ա�Ļ���Ϊ:" + member.getIntegral());
            return member;
        } else {
            System.out.println("�û�Ա�����ڣ�");
            return null;
        }

    }

    /**
     * ֱ���޸Ļ�Ա����
     */
    @Override
    public void modifyIntegral() {
        System.out.println("������Ҫ�޸Ļ�Ա���ֵĻ�Ա��:");
        String tempId = input.next();
        Member_login member = memberLogin.findMemberById_login(tempId);
        if (JmemberManage_login.search(tempId) == false) {
            System.out.println("�û�Ա�����ڣ��޸�ʧ��!");
        } else {
            System.out.println("������Ҫ�޸ĵĻ�����:");
            int tempIntegral = input.nextInt();
            member.setIntegral(tempIntegral);
            JmemberManage_login.modifyIntegral(tempId, tempIntegral);
            System.out.println("�޸ĳɹ�!");
        }

    }

    /**
     * �������ӻ�Ա����
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
