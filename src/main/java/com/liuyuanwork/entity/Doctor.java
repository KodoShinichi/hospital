package com.liuyuanwork.entity;

import java.util.Scanner;
import com.liuyuanwork.customexception.ExceptionCatch;
import com.liuyuanwork.index.Patient;

/**
 * 医生类(Doctor)
 * @author 刘芫
 * @version 2.0
 */

public class Doctor extends Hospital {

    // 创建对象
    Scanner input = new Scanner(System.in);
    MenusOpt ms = new MenusOpt(); // 菜单显示对象
    ExceptionCatch ec = new ExceptionCatch();

    /**
     * 挂号方法
     */
    public void register() {
        int pos = 0;
        for (int i = 0; i < list.size() && list.get(i) != null; i++) {
            pos++;
        }
        System.out.print("\n请您输入您的姓名：");
        String name = input.next();
        System.out.print("请您输入您的性别：");
        String gender = ec.catchGenderEx();
        System.out.print("请您输入您的年龄：");
        int age = ec.catchAgeEx();
        System.out.print("请您输入您的手机号：");
        long telephone = ec.catchTeleEx();
        System.out.println("\n选择挂号的科室");
        String[] arr = ms.chooseDepart(); // 显示二级菜单
        System.out.print("请您做出选择：");
        int departOpt = chooseOpt(arr); // 用户选择方法
        String department = "";
        switch (departOpt) {
            case 1:
                department = "消化科";
                break;
            case 2:
                department = "神经内科";
                break;
            case 3:
                department = "心脏科";
                break;
            case 4:
                return;
        }
        list.add(new Patient(pos+1,name,gender,age,telephone,department,"开始:无检查","无状态",0.0,0.0,0.0,"空",0.0));
        Patient p = list.get(pos);
        System.out.println("挂号成功！请记住您的挂号信息随后到开具药方处问诊");
        System.out.println("挂号号码："+ p.getId() + " , 姓名：" + p.getName() + " , 性别：" + p.getGender() + " , 手机号：" + p.getTelephone() + " , 挂号科室：" + p.getDepartment());
    }

    /**
     * 收费方法
     */
    public void toll() {
        Patient p = match(); // 匹配挂号
        if (p != null) {
            String c = p.getCheckState(); // 检查状态获取
            double m = p.getCheckMoney(); // 缴费金额
            String d = p.getBuyDrugState(); //买药状态获取
            // 匹配需要缴费的项目
            if (c.equals("CT:待检查") && m == 0.0) {
                System.out.print("\n是否缴纳CT的费用200.0元(y/n)：");
                p.setCheckMoney((ifMoney(input.next(), 200.0, "CT")));
            } else if (c.equals("核磁共振:待检查") && m == 0.0) {
                System.out.println("\n是否缴纳核磁共振的费用800.0元(y/n)：");
                p.setCheckMoney((ifMoney(input.next(), 800.0, "CT")));
            } else if (c.equals("心电图:待检查") && m == 0.0) {
                System.out.println("\n是否缴纳心电图的费用100.0元(y/n)：");
                p.setCheckMoney((ifMoney(input.next(), 100.0, "CT")));
            } else if (d.equals("已批价")) {
                System.out.println("\n是否缴纳药品的费用元(y/n)：");
                double im = ifMoney(input.next(), 1000, "药品");
                if (im != 0.0) {
                    p.setMedicineMoney(im);
                    p.setBuyDrugState("已缴费");
                }
            } else {
                System.out.println("\n您没有需要缴费的项目！");
            }
        }
    }
    /**
     * 医生项目检查方法
     */
    public String docProCheck(Patient p){
        String state = p.getCheckState();
        String[] c = state.split(":");
        double give = p.getCheckMoney(); // 获取用户CT的缴费情况
        if(give == 0.0 && "待检查".equals(c[1])){
            // 没交费待检查的情况
            System.out.println("\n您没有缴费，请缴费后再过来检查！");
            return "待检查";
        }else if(give != 0.0 && "待检查".equals(c[1])){
            // 缴费了待检查的情况
            System.out.println("\n开始检查............检查结束！请回到医生处开具药方");
            return "已检查";
        }else if(give != 0.0 && "已检查".equals(c[1])){
            // 缴费了 已检查的情况
            System.out.println("\n您已经检查过了，无需再次检查！");
            return "已检查";
        }else if("医生已开药".equals(c[1])){
            // 已经开药的情况
            System.out.println("您都开药了跑过来干嘛！滚去药房！");
            return "医生已开药";
        }
        return "无检查";
    }
    /**
     * 医生就诊方法
     */
    public String doctorCheck(Patient p,String pro){
        String state = p.getCheckState();
        String[] c = state.split(":");
        switch (c[1]) {
            case "无检查":
                // 无检查的状态则进行看病
                System.out.println("望闻问切。。。。。。。。。。。。。");
                System.out.println("建议您去做一下 " + pro + " 检查再回来开药方吧");
                return "待检查";
            case "待检查":
                // 待检查的状态则提醒病人去检查项目
                System.out.println("请检查完" + c[0] + "再过来！");
                return "待检查";
            case "已检查":
                // 已检查的状态则给病人开药
                System.out.println("正在为您开药。。。。。。。。。。。。。。已开药");
                return "医生已开药";
            default:
                // 其他情况则是医生已完成就诊
                System.out.println("就诊结束了就不要过来了，去拿药吧");
                return "医生已开药";
        }
    }

    /**
     * 医生批价方法
     */
/*    public double evaluatePrice() {
        Patient hp = match();// 匹配挂号
        if (hp != null) {
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
    }*/

    /**
     * 发药方法
     */
    /*public void giveDrug() {
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
        }
    }
*/
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