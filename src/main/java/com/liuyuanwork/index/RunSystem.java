package com.liuyuanwork.index;

import com.liuyuanwork.entity.MenusOpt;
import com.liuyuanwork.entity.Patient;
import java.util.Scanner;

/**
 * 医院系统主程序入口
 * @author 刘芫
 * @version 2.0
 */
public class RunSystem {
    //public static Logger logger = Logger.getLogger(Patient.class);
    public static void main(String[] args) {
        // 实例对象
        Scanner input = new Scanner(System.in);
        MenusOpt ms = new MenusOpt();
        Patient pe = new Patient();
        while (true) {
            ms.showIndexMenus();// 打印医院系统页面
            System.out.print("请选择您的操作：");
            String opt = input.next();
            switch (opt) {
                case "1":
                    // 挂号收费
                    pe.registeredCharging(opt);
                    break;
                case "2":
                    // 拍片检查
                    pe.chooseCheck(opt);
                    break;
                case "3":
                    // 问诊处
                    pe.chooseDoctor();
                    break;
                case "4":
                    // 药房取药
                    pe.chooseMedicine(opt);
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
