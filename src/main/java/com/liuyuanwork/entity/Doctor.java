package com.liuyuanwork.entity;


import java.util.Scanner;

import com.liuyuanwork.customexception.GenderException;
import com.liuyuanwork.interfacedemo.*;

/**
 * 医生类(Doctor)
 * @author 刘芫
 * @version 2.0
 */

public class Doctor extends Hospital {
    public static double rs;
    Scanner input = new Scanner(System.in);
    ctCheck ctDoc = new ctCheck(); // 消化科类对象
    nmrCheck nmrDoc = new nmrCheck(); // 神经内科实现类对象
    heartCheck htDoc = new heartCheck(); // 心脏科实现类对象
    MenusOpt ms = new MenusOpt();
    //	Drug dg = new Drug();
    static Drug[] drugArr = {
            new Drug("奥美拉唑", 112.0),
            new Drug("雷贝拉唑", 55.0),
            new Drug("阿莫西林", 20.5),
            new Drug("阿司匹林", 80.0),
            new Drug("瑞舒伐他汀", 58.2),
            new Drug("单硝酸异山梨酯缓释片", 100.0),
            new Drug("钙离子拮抗剂", 68.3),
            new Drug("醛固酮受体拮抗剂", 30.2)
    };

    /**
     * 挂号方法
     */
    public void register() {
        int pos = 0;
        for (int i = 0; i < sickArr.length && sickArr[i] != null; i++) {
            pos++;
        }
        sickArr[pos] = new Hospital();
        sickArr[pos].setId(pos + 1);
        System.out.print("\n请您输入您的姓名：");
        sickArr[pos].setName(input.next());
        while (true) {
            try {
                System.out.print("请您输入您的性别：");
                String gender = input.next();
                if (gender.equals("男") || gender.equals("女")) {
                    sickArr[pos].setGender(gender);
                    break;
                } else {
                    throw new GenderException();
                }
            } catch (GenderException e) {
                System.out.println("请输入男或女！");
            }
        }
        while(true) {
            try {
                System.out.print("请您输入您的年龄：");
                int age = input.nextInt();
                sickArr[pos].setAge(age);
                break;
            } catch (Exception e) {
                System.out.println("请输入正确的年龄");
                input.next();
            }
        }
        System.out.print("请您输入您的手机号：");
        sickArr[pos].setTelephone(input.next());
        boolean flag = true;
        do {
            ms.chooseDepart();
            System.out.print("请您输入您要挂号的科室：");
            String departOpt = input.next();
            switch (departOpt) {
                case "1":
                    sickArr[pos].setDepartment("消化科");
                    flag = false;
                    break;
                case "2":
                    sickArr[pos].setDepartment("神经内科");
                    flag = false;
                    break;
                case "3":
                    sickArr[pos].setDepartment("心脏科");
                    flag = false;
                    break;
                default:
                    System.out.println("您的选择有误！请重新输入：");
                    flag = true;
            }
        } while (flag);

        // 打印信息
        System.out.println("\n挂号成功！您的挂号信息：\n");
        System.out.println("号码\t姓名\t性别\t手机号\t\t科室");
        System.out.println(
                sickArr[pos].getId() + "\t"
                        + sickArr[pos].getName() + "\t"
                        + sickArr[pos].getGender() + "\t"
                        + sickArr[pos].getTelephone() + "\t"
                        + sickArr[pos].getDepartment()
        );
        System.out.println("\n请到开具药方处问诊！\n");
    }

    /**
     * 收费方法
     */
    public void toll(double rsPrice) {
        Hospital hp = match(); // 匹配挂号
        if (hp != null) {
            String c = hp.getCtState(); // CT检查状态获取
            String n = hp.getNmrState(); // 核磁共振检查状态获取
            String h = hp.getHeartState(); // 心电图检查状态获取
            String d = hp.getBuyDrugState(); //买药状态获取
            // 匹配需要缴费的项目
            if (c != null && c.equals("待检查") && hp.getCtMoney() == 0.0) {

                System.out.print("\n是否缴纳CT的费用200.0元(y/n)：");
                hp.setCtMoney(ifMoney(input.next(), 200.0, "CT"));

            } else if (n != null && n.equals("待检查") && hp.getNmrMoney() == 0.0) {

                System.out.println("\n是否缴纳核磁共振的费用800.0元(y/n)：");
                hp.setNmrMoney(ifMoney(input.next(), 800.0, "核磁共振"));

            } else if (h != null && h.equals("待检查") && hp.getHeartMoney() == 0.0) {

                System.out.println("\n是否缴纳心电图的费用100.0元(y/n)：");
                hp.setHeartMoney(ifMoney(input.next(), 100.0, "心电图"));

            } else if (d != null && d.equals("已批价")) {

                System.out.println("\n是否缴纳药品的费用" + rsPrice + "元(y/n)：");
                double im = ifMoney(input.next(), rsPrice, "药品");
                if (im != 0.0) {
                    hp.setMedicineMoney(im);
                    hp.setBuyDrugState("已缴费");
                }
            } else {
                System.out.println("\n您没有需要缴费的项目！");
            }
        } else {
            System.out.println("\n您未挂号，请挂号后再过来！");
        }
    }

    /**
     * 调用接口 向上转型
     */
    public void choseDoc(CheckPro cp, Hospital hp) {
        cp.docCheck(hp);
    }

    public void choseAi(CheckPro cp) {
        cp.aiCheck();
    }

    /**
     * CT检查方法
     */
    public void ctCheck() {
        choseAi(ctDoc); // 向上转型使用ct检查方法
    }

    /**
     * 核磁共振方法
     */
    public void nmrCheck() {
        choseAi(nmrDoc); // 向上转型使用核磁共振检查方法
    }

    /**
     * 心电图方法
     */
    public void heartCheck() {
        choseAi(htDoc); // 向上转型使用心电图检查方法
    }

    /**
     * 根据科室分配医生
     */
    public void makePrescription() {
        Hospital hp = match();// 匹配挂号
        if (hp != null) {
            switch (hp.getDepartment()) {
                case "消化科":
                    choseDoc(ctDoc, hp);
                    break;
                case "神经内科":
                    choseDoc(nmrDoc, hp);
                    break;
                case "心脏科":
                    choseDoc(htDoc, hp);
                    break;
            }
        } else {
            System.out.println("您未挂号，请挂号后再过来！");
        }
    }

    /**
     * 批价方法
     */
    public double evaluatePrice() {
        Hospital hp = match();// 匹配挂号
        if (hp != null) {
            String str = hp.getDrugOrders(); //获取药方
            String state = hp.getBuyDrugState(); // 获取买药状态
            double sta = hp.getMedicineMoney(); // 获取买药花费
            if (str != null && sta == 0.0 && state.equals("待批价")) {
                System.out.println("\n正在为您的药方计算价格.......");
                System.out.println("\n........................。");
                rs = calcPrice(str);
                hp.setBuyDrugState("已批价");
                System.out.println("\n您的药方价格已批出，请回到挂号收费处，缴纳药费，最后去药店取药。");
                return rs;
            } else if ("已批价".equals(state)) {
                System.out.println("\n您已经批过价格了！");
                return rs;
            } else {
                System.out.println("\n您没有药方来批什么价？回去医生那开药方谢谢！");
            }
        } else {
            System.out.println("您未挂号，请挂号后再过来！");
        }
        return 0.0;
    }

    /**
     * 发药方法
     */
    public void giveDrug() {
        Hospital hp = match();// 匹配挂号
        if (hp != null) {
            double rs = hp.getMedicineMoney(); // 获取药方费用
            String state = hp.getBuyDrugState(); // 获取买药状态
            if (state != null && state.equals("待批价")) {
                // 没有批价的情况
                System.out.println("您没有给药方批价，请完成再来！");
            } else if (state != null && rs == 0.0 && state.equals("已批价")) {
                // 已批价没有缴费的情况
                System.out.println("您没有去收费处缴纳药费，请完成再来！");
            } else if (state != null && rs != 0.0 && state.equals("已缴费")) {
                // 已批价已缴费的情况
                System.out.println("正在为您取药..........");
                hp.setBuyDrugState("已取药");
                System.out.println("已完成取药，请收好药，回家好好养病吧！");
            } else if (state != null && state.equals("已取药")) {
                // 已批价已缴费已取药的情况下
                System.out.println("您已经取过药了！快回家去好好养病吧！");
            } else {
                System.out.println("您前面的项目啥都没做，您来找打吗？");
            }
        } else {
            System.out.println("您未挂号，请挂号后再过来！");
        }
    }

    /**
     * 计算药品价格
     */
    public double calcPrice(String str) {
        String[] userDrug = str.split(",");
        double rs = 0.0;
        for (int i = 0; i < userDrug.length; i++) {
            String ud = userDrug[i];
            for (int j = 0; j < drugArr.length; j++) {
                if (ud.equals(drugArr[j].getDrugname())) {
                    rs += drugArr[j].getDrugPrice(); // 计算药单价格
                }
            }
        }
        return rs;
    }
}