package com.liuyuanwork.entity;

/**
 *药品类(Drug)
 *@author 刘芫
 *@version 2.0
 */

public class Drug {
	private String drugname; // 药品名称
	private double drugPrice; // 药品价格

	public Drug(){

	}

	public Drug(String drugname,double drugPrice){
		this.drugname = drugname;
		this.drugPrice = drugPrice;
	}


	/**
	 *getter 和 setter 方法
	 */
	public String getDrugname(){
		return drugname;
	}
	public void setDrugname(String drugname){
		this.drugname = drugname;
	}
	public double getDrugPrice(){
		return drugPrice;
	}
	public void setDrugPrice(double drugPrice){
		this.drugPrice = drugPrice;
	}
}