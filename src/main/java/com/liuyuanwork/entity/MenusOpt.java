package com.liuyuanwork.entity;

/**
 *菜单输出类(MenusOpt)
 *@author 刘芫
 *@version 1.0
 */

public class MenusOpt{
	// 首页菜单显示
	public void showIndexMenus() {
		String[] arr = {"挂号收费","拍片检查","医生就诊","药房取药","退出系统"};
		System.out.println("\n=============================欢迎使用医院智能系统==========================\n");
		printArr(arr);
		System.out.println("==========================================================================\n");
	}

	// 根据用户选择的一级菜单输出不同的二级菜单
	public String[] showSecondMenus(String opt) {
		switch(opt){
			case "1":
				String[] arrOne = {"挂号","收费","返回上一级菜单"};
				System.out.println("\n=============================挂号、收费======================================\n");
				printArr(arrOne);
				System.out.print("================================================================================\n");
				return arrOne;
			case "2":
				String[] arrTwo = {"CT","核磁共振","心电图","返回上一级菜单"};
				System.out.println("\n=============================拍片检查====================================\n");
				printArr(arrTwo);
				System.out.print("=============================================================================\n");
				return arrTwo;
			case "4":
				String[] arrThree = {"批价","发药","返回上一级菜单"};
				System.out.println("\n=============================药房====================================\n");
				printArr(arrThree);
				System.out.print("=============================================================================\n");
				return arrThree;
		}
		return null;
	}
	// 打印科室菜单
	public String[] chooseDepart(){
		String[] arr = {"消化科","神经科","心脏科","返回上一级菜单"};
		printArr(arr);
		return arr;
	}
	// 循环输出数组方法
	public void printArr(String[] arr) {
		for (int i=0; i<arr.length; i++) {
			System.out.println((i+1) + "、" + arr[i]);
		}
		System.out.println();
	}
}