package com.hiro.hotel.entity.login;

/**
 * @author XX
 * �����࣬�൱���˵�
 */
public class Expense_login {
	
	private String remarks;  //���ñ�ע
	private double totalMoney;  //�ܵĽ��

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getTotalMoney() {
		return totalMoney;
	}
	
	public Expense_login(){
		
	}
	
	public Expense_login(String remarks, double totalMoney){
		this.remarks=remarks;
		this.totalMoney=totalMoney;
	}

}
