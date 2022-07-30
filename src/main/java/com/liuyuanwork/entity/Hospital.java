package com.liuyuanwork.entity;

import java.util.Scanner;
import java.util.Random;

/**
 *医院父类(Hospital)
 * @author 刘芫
 * @since jdk18
 * @version 2.0
 */

public class Hospital{
    /*
    * 起床后修改*/
    private int id; // 登记号
    private String name; // 姓名
    private String gender; // 性别
    private int age; // 年龄
    private String telephone; // 电话
    private String department; // 就诊科室
    private String ctState; // 检查CT状态
    private String nmrState; // 检查核磁共振状态
    private String heartState; // 检查心电图状态
    private String buyDrugState; // 买药状态
    private double ctMoney; // CT费用
    private double nmrMoney; // 核磁工资费用
    private double heartMoney; // 心电图费用
    private String drugOrders; // 药方
    private double medicineMoney; // 药方费用
    private double sumPrice; // 总费用

    static Hospital[] sickArr = new Hospital[20]; // 病例单对象数组
    // 无参构造器
    public Hospital() {
    }
    // 有参构造器
	/* public Hospital(int id, String name, String gender, int age, String telephone, String department, String ctState, String nmrState, String heartState, String buyDrugState, double ctMoney, double nmrMoney, double heartMoney, double sumPrice) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telephone = telephone;
        this.department = department;
        this.ctState = ctState;
        this.nmrState = nmrState;
        this.heartState = heartState;
        this.buyDrugState = buyDrugState;
        this.ctMoney = ctMoney;
        this.nmrMoney = nmrMoney;
        this.heartMoney = heartMoney;
        this.sumPrice = sumPrice;
    } */
    // 重写toString方法
    // @Override
	/* public String toString(){
		return id + "," + name + "," + gender + "," + age + "," + telephone + "," + department + "," + sumPrice;
	} */

    /**
     *匹配是否挂号方法
     */
    Scanner input = new Scanner(System.in);
    Random rand = new Random();
    public Hospital match(){
        System.out.print("请您输入您的挂号号码：");
        int id = input.nextInt();
        for (int i=0; i<sickArr.length && sickArr[i] != null; i++){
            if(id == sickArr[i].getId()){
                return sickArr[i];
            }
        }
        return null;
    }

    /**
     *判断用户是否想缴费
     */
    public double ifMoney(String ans,double cost,String n) {
        if ("y".equals(ans)){
            System.out.println("\n您的"+ n +"费用已缴纳！！");
            return cost;
        }else {
            System.out.println("\n拜拜！");
        }
        return 0.0;
    }

    /**
     *getter 和 setter 方法
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCtState() {
        return ctState;
    }

    public void setCtState(String ctState) {
        this.ctState = ctState;
    }

    public String getNmrState() {
        return nmrState;
    }

    public void setNmrState(String nmrState) {
        this.nmrState = nmrState;
    }

    public String getHeartState() {
        return heartState;
    }

    public void setHeartState(String heartState) {
        this.heartState = heartState;
    }

    public String getBuyDrugState() {
        return buyDrugState;
    }

    public void setBuyDrugState(String buyDrugState) {
        this.buyDrugState = buyDrugState;
    }

    public double getCtMoney() {
        return ctMoney;
    }

    public void setCtMoney(double ctMoney) {
        this.ctMoney = ctMoney;
    }

    public double getNmrMoney() {
        return nmrMoney;
    }

    public void setNmrMoney(double nmrMoney) {
        this.nmrMoney = nmrMoney;
    }

    public double getHeartMoney() {
        return heartMoney;
    }

    public void setHeartMoney(double heartMoney) {
        this.heartMoney = heartMoney;
    }

    public String getDrugOrders() {
        return drugOrders;
    }

    public void setDrugOrders(String drugOrders) {
        this.drugOrders = drugOrders;
    }

    public double getMedicineMoney() {
        return medicineMoney;
    }

    public void setMedicineMoney(double medicineMoney) {
        this.medicineMoney = medicineMoney;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
}