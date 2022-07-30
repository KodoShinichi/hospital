package com.liuyuanwork.index;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.entity.Hospital;
import com.liuyuanwork.entity.MenusOpt;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 *病人(Patient) 主程序入口
 *@author 刘芫
 *@version 2.0
 */

public class Patient extends Hospital {

	static double rsPrice; // 传递批价
	String secondOpt; // 二级菜单选择
	String ans; // 回答

	// 创建对象
	MenusOpt ms = new MenusOpt(); // 显示菜单对象
	Doctor dc = new Doctor(); // 医生类对象
	Scanner input = new Scanner(System.in);


	/**
	 *病人选择挂号收费
	 */
	public void registered(String opt) {
		ms.showSecondMenus(opt); // 二级菜单显示
		System.out.print("请选择您需要的功能：");
		secondOpt = input.next();
		switch(secondOpt){
			case "1":
				dc.register();// 挂号功能
				break;
			case "2":
				dc.toll(rsPrice);// 收费功能
				break;
			default:
				System.out.println("您的输入有误！");
		}
	}

	/**
	 *病人选择拍片检查
	 */
	public void checkPro(String opt){
		ms.showSecondMenus(opt);// 二级菜单显示
		System.out.print("请选择您需要的功能：");
		secondOpt = input.next();
		switch(secondOpt){
			case "1":
				dc.ctCheck();// CT
				break;
			case "2":
				dc.nmrCheck(); // 核磁共振
				break;
			case "3":
				dc.heartCheck();// 心电图
				break;
			default:
				System.out.println("您的输入有误");
		}
	}

	/**
	 *病人选择开具药方
	 */
	public void seeDoctor(){
		dc.makePrescription(); // 医生就诊
	}

	/**
	 *病人选择药房取药
	 */
	public void getMedicine(String opt){
		ms.showSecondMenus(opt);// 二级菜单显示
		System.out.print("请选择您需要的功能：");
		secondOpt = input.next();
		switch(secondOpt){
			case "1":
				rsPrice = dc.evaluatePrice();// 批价
				break;
			case "2":
				dc.giveDrug();// 取药
				break;
			default:
				System.out.println("您的输入有误");
		}
	}
	public static Logger logger = Logger.getLogger(Patient.class);
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 菜单选择
		String opt = "";
		// 创建对象
		MenusOpt ms = new MenusOpt();
		Patient pe = new Patient();
		while(true){
			ms.showIndexMenus();// 打印医院系统页面
			System.out.print("请选择您的操作：");
			opt = input.next();
			switch (opt){
				case "1":
					// 挂号收费
					pe.registered(opt);
					break;
				case "2":
					// 拍片检查
					pe.checkPro(opt);
					break;
				case "3":
					// 问诊处
					pe.seeDoctor();
					break;
				case "4":
					// 药房取药
					pe.getMedicine(opt);
					break;
				case "5":
					// 退出系统
					System.out.println("\n感谢您使用本医院系统，祝您早日康复，再见！");
					System.exit(0);
					break;
				default:
					System.out.println("输入错误请重新选择！");
			}
		}
	}
}