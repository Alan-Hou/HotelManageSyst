package com.hiro.hotel.entity.login;

/**
 * @author XX
 * 费用类，相当于账单
 */
public class Expense_login {
	
	private String remarks;  //费用备注
	private double totalMoney;  //总的金额

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
