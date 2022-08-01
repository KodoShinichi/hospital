package com.liuyuanwork.entity;

import com.liuyuanwork.customexception.ExceptionCatch;

/**
 * 病人类(Patient)
 * @author 刘芫
 * @version 2.0
 */

public class Patient {
    private int id; // 登记号
    private String name; // 姓名
    private String gender; // 性别
    private int age; // 年龄
    private long telephone; // 电话
    private String Department; // 科室
    private String checkState; // 检查状态
    private String buyDrugState; // 买药状态
    private double checkMoney; // 检查费用
    private double drugCalcPrice; // 药方待交金额
    private double medicineMoney; // 药方费用
    private String drugTxt; // 药方
    private double sumPrice; // 总费用

    public Patient() {
    }

    public Patient(int id, String name, String gender, int age, long telephone, String department, String checkState, String buyDrugState, double checkMoney, double drugCalcPrice, double medicineMoney, String drugTxt, double sumPrice) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telephone = telephone;
        this.Department = department;
        this.checkState = checkState;
        this.buyDrugState = buyDrugState;
        this.checkMoney = checkMoney;
        this.drugCalcPrice = drugCalcPrice;
        this.medicineMoney = medicineMoney;
        this.drugTxt = drugTxt;
        this.sumPrice = sumPrice;
    }

    // 创建对象
    MenusOpt ms = new MenusOpt(); // 显示菜单对象
    ExceptionCatch ec = new ExceptionCatch(); // 异常捕捉对象
    Doctor dc = new Doctor(); // 医生类对象


    /**
     * 病人选择挂号收费
     */
    public void registeredCharging(String firstOpt) {
        String[] arr = ms.showSecondMenus(firstOpt);
        int secondOpt = ec.chooseOpt(arr);
        switch (secondOpt) {
            case 1:
                chooseRegister();
                break;
            case 2:
                chooseToll();
                break;
            case 3:
                break;
        }
    }

    /**
     * 病人选择挂号
     */
    public void chooseRegister() {
        dc.register();
    }

    /**
     * 病人选择收费
     */
    public void chooseToll() {
        dc.allotToll();
    }

    /**
     * 病人选择拍片检查
     */
    public void chooseCheck(String firstOpt) {
        dc.allotCheck(firstOpt);
    }

    /**
     * 病人选择医生就诊
     */
    public void chooseDoctor() {
        dc.allotDoctor();
    }

    /**
     * 病人选择药房取药
     */
    public void chooseMedicine(String firstOpt) {
        String[] arr = ms.showSecondMenus(firstOpt);
        int secondOpt = ec.chooseOpt(arr);
        switch (secondOpt) {
            case 1:
                dc.evaluatePrice(); // 批价
                break;
            case 2:
                dc.giveDrug();// 取药
                break;
            case 3:
                break;
        }
    }

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

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getBuyDrugState() {
        return buyDrugState;
    }

    public void setBuyDrugState(String buyDrugState) {
        this.buyDrugState = buyDrugState;
    }

    public double getCheckMoney() {
        return checkMoney;
    }

    public void setCheckMoney(double checkMoney) {
        this.checkMoney = checkMoney;
    }

    public double getDrugCalcPrice() {
        return drugCalcPrice;
    }

    public void setDrugCalcPrice(double drugCalcPrice) {
        this.drugCalcPrice = drugCalcPrice;
    }

    public double getMedicineMoney() {
        return medicineMoney;
    }

    public void setMedicineMoney(double medicineMoney) {
        this.medicineMoney = medicineMoney;
    }

    public String getDrugTxt() {
        return drugTxt;
    }

    public void setDrugTxt(String drugTxt) {
        this.drugTxt = drugTxt;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
}