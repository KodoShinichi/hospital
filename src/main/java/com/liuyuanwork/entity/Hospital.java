package com.liuyuanwork.entity;

import com.liuyuanwork.customexception.ExceptionCatch;
import com.liuyuanwork.implementation.Digestive;
import com.liuyuanwork.implementation.Heart;
import com.liuyuanwork.implementation.Neurology;
import com.liuyuanwork.implementation.Pharmacy;
import com.liuyuanwork.interfacepro.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 医院父类(Hospital)
 * @author 刘芫
 * @version 2.0
 * @since jdk1.8
 */

public class Hospital {

    public static final ArrayList<Patient> list = new ArrayList<>();

    public static Drug[] drugArr = {
            new Drug("奥美拉唑", 112.0),
            new Drug("雷贝拉唑", 55.0),
            new Drug("阿莫西林", 20.5),
            new Drug("阿司匹林", 80.0),
            new Drug("瑞舒伐他汀", 58.2),
            new Drug("单硝酸异山梨酯缓释片", 100.0),
            new Drug("钙离子拮抗剂", 68.3),
            new Drug("醛固酮受体拮抗剂", 30.2)
    };

    // 实例对象
    ExceptionCatch ec = new ExceptionCatch();
    Scanner sc = new Scanner(System.in);
    MenusOpt ms = new MenusOpt(); // 显示菜单对象
    Digestive ctDoc = new Digestive(); // 消化科实现类对象
    Neurology nmrDoc = new Neurology(); // 神经内科实现类对象
    Heart htDoc = new Heart(); // 心脏科实现类对象
    Pharmacy pmy = new Pharmacy(); // 药方实现类对象


    /**
     * 调用接口
     */
    public void chooseAi(CheckPro cp, Patient p) {
        cp.aiCheck(p);
    }
    public void chooseDoc(CheckPro cp, Patient p) {
        cp.docCheck(p);
    }
    public void choosePay(ChargePro cgp,Patient p){
        cgp.toll(p);
    }

    /**
     * 匹配是否挂号方法
     */
    public Patient match() {
        System.out.print("请您输入您的挂号号码：");
        int id = ec.catchIntEx();
        for (int i = 0; i < list.size() && list.get(i) != null; i++) {
            if (id == list.get(i).getId()) {
                return list.get(i);
            }
        }
        System.out.println("您未挂号，请挂号后再过来！");
        return null;
    }

    /**
     * 判断病人是否想缴费
     */
    public double ifMoney(String n, double cost) {
        System.out.print("\n是否缴纳" + n + "的费用" + cost + "元(y/n)：");
        String ans = sc.next();
        if ("y".equals(ans)) {
            System.out.println("\n您的" + n + "费用已缴纳！！");
            return cost;
        } else {
            System.out.println("\n拜拜！");
            return 0.0;
        }
    }

    /**
     * 判断病人是否有检查项目
     */
    public boolean ifCheckPro(Patient p){
        if ("开始".equals(splitString(p)[0])) {
            System.out.println("您没有需要检查的项目！");
            return false;
        }
        return true;
    }

    /**
     * 病人检查状态传递
     */
    public String[] splitString(Patient p) {
        String state = p.getCheckState();
        return state.split(":");
    }

    /**
     * 缴费科室分配
     */
    public void allotToll(){
        Patient p = match(); // 匹配挂号
        if (p != null) {
            String c = p.getCheckState(); // 检查状态获取
            String d = p.getBuyDrugState(); // 买药状态获取
            double checkMoney = p.getCheckMoney(); // 检查缴费金额
            double medicineMoney = p.getMedicineMoney(); // 药方缴费金额
            if(checkMoney == 0.0){
                switch (c) {
                    case "CT:待检查":
                        choosePay(ctDoc,p);
                        return;
                    case "核磁共振:待检查":
                        choosePay(nmrDoc,p);
                        return;
                    case "心电图:待检查":
                        choosePay(htDoc,p);
                        return;
                    default:
                        System.out.println("\n您没有需要缴费的项目！");
                        return;
                }
            }
            if (medicineMoney == 0.0 && d.equals("已批价")) {
                choosePay(pmy, p);
            }else {
                System.out.println("\n您没有需要缴费的项目！");
            }
        }
    }

    /**
     * 检查科室分配
     */
    public void allotCheck(String firstOpt) {
        Patient p = match();
        if(p != null && ifCheckPro(p)) {
            String[] arr = ms.showSecondMenus(firstOpt);
            int secondOpt = ec.chooseOpt(arr);
            switch (secondOpt) {
                case 1:
                    chooseAi(ctDoc,p);
                    break;
                case 2:
                    chooseAi(nmrDoc,p);
                    break;
                case 3:
                    chooseAi(htDoc,p);
                    break;
                case 4:
                    break;
            }
        }
    }

    /**
     * 就诊科室分配
     */
    public void allotDoctor() {
        Patient p = match();
        if(p != null){
            switch (p.getDepartment()) {
                case "消化科":
                    chooseDoc(ctDoc,p);
                    break;
                case "神经内科":
                    chooseDoc(nmrDoc,p);
                    break;
                case "心脏科":
                    chooseDoc(htDoc,p);
                    break;
                default:
            }
        }
    }
}