package com.liuyuanwork.entity;

/**
 * 医生类(Doctor)
 * @author 刘芫
 * @version 2.0
 */

public class Doctor extends Hospital {

    /**
     * 医生给病人挂号方法
     */
    public void register() {
        int pos = 0;
        for (int i = 0; i < list.size() && list.get(i) != null; i++) {
            pos++;
        }
        System.out.print("\n请您输入您的姓名：");
        String name = sc.next();
        System.out.print("请您输入您的性别：");
        String gender = ec.catchGenderEx();
        System.out.print("请您输入您的年龄：");
        int age = ec.catchIntEx();
        System.out.print("请您输入您的手机号：");
        long telephone = ec.catchLongEx();
        System.out.println("\n选择挂号的科室");
        String[] arr = ms.chooseDepart(); // 显示二级菜单
        System.out.print("请您做出选择：");
        int departOpt = ec.chooseOpt(arr); // 用户选择方法
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
        list.add(new Patient(pos + 1, name, gender, age, telephone, department, "开始:无检查", "无状态", 0.0, 0.0, 0.0, "空", 0.0));
        Patient p = list.get(pos);
        System.out.println("挂号成功！请记住您的挂号信息随后到开具药方处问诊");
        System.out.println("挂号号码：" + p.getId() + " , 姓名：" + p.getName() + " , 性别：" + p.getGender() + " , 手机号：" + p.getTelephone() + " , 挂号科室：" + p.getDepartment());
    }

    /**
     * 医生项目检查方法
     */
    public boolean docProCheck(Patient p) {
        String state = p.getCheckState(); // 获取用户检查状态
        String drugState = p.getBuyDrugState(); // 获取用户买药状态
        String[] c = state.split(":");
        double give = p.getCheckMoney(); // 获取用户CT的缴费情况
        if (give == 0.0 && "待检查".equals(c[1])) {
            // 没交费待检查的情况
            System.out.println("\n您没有缴费，请缴费后再过来检查！");
        } else if (give != 0.0 && "已检查".equals(c[1])) {
            // 缴费了 已检查的情况
            System.out.println("\n您已经检查过了，无需再次检查！");
        } else if (drugState.equals("已取药")) {
            // 已经取药的情况
            System.out.println("您已完成所有流程！快回家去好好养病吧！");
        } else if ("医生已开药".equals(c[1])) {
            // 已经开药的情况
            System.out.println("您都开药了跑过来干嘛！滚去药房！");
        }  else if (give != 0.0 && "待检查".equals(c[1])) {
            // 缴费了待检查的情况
            System.out.println("\n开始检查............检查结束！请回到医生处开具药方");
            return true;
        }
        return false;
    }

    /**
     * 医生就诊方法
     */
    public String doctorCheck(Patient p, String pro, String DrugTxt) {
        String state = p.getCheckState(); // 获取用户检查状态
        String[] c = state.split(":");
        if ("无检查".equals(c[1])) {// 无检查的状态则进行看病
            System.out.println("望闻问切。。。。。。。。。。。。。");
            System.out.println("建议您去做一下 " + pro + " 检查再回来开药方吧");
            return "待检查";
        } else if ("待检查".equals(c[1])) {// 待检查的状态则提醒病人去检查项目
            System.out.println("请检查完" + c[0] + "再过来！");
            return "待检查";
        } else if ("已检查".equals(c[1])) {// 已检查的状态则给病人开药
            System.out.println("正在为您开药。。。。。。。。。。。。。。已开药");
            System.out.println("您的药方为：" + DrugTxt + "  请去药房批价吧");
            return "医生已开药";
        } else {
            System.out.println("就诊结束了！");
            return "医生已开药";
        }
    }

    /**
     * 医生批价方法
     */
    public void evaluatePrice() {
        Patient hp = match();// 匹配挂号
        if (hp != null) {
            String state = splitString(hp)[1]; // 病人就诊状态
            String drugTxt = hp.getDrugTxt(); // 病人药方
            String buyState = hp.getBuyDrugState(); // 病人买药状态
            if (buyState.equals("已批价") || buyState.equals("已缴费")) {
                System.out.println("您已经批过价格了！");
            } else if (buyState.equals("已取药")) {
                System.out.println("您已经取药了，赶紧回家去吧！");
            } else if (state.equals("医生已开药") && buyState.equals("待批价")) {
                System.out.println("正在为您的药方计算价格.......");
                hp.setDrugCalcPrice(calcPrice(drugTxt)); // 修改待买药金额
                hp.setBuyDrugState("已批价"); // 修改买药状态
                System.out.println("您的药方价格已批出，请回到挂号收费处，缴纳药费，最后去药店取药。");
            } else {
                System.out.println("您没有药方来批什么价？");
            }
        }
    }

    /**
     * 医生发药方法
     */
    public void giveDrug() {
        Patient p = match();// 匹配挂号
        if (p != null) {
            double rs = p.getMedicineMoney(); // 获取药方费用
            String state = p.getBuyDrugState(); // 获取买药状态
            if (state.equals("待批价")) {
                // 没有批价的情况
                System.out.println("您没有给药方批价，请完成再来！");
            } else if (rs == 0.0 && state.equals("已批价")) {
                // 已批价没有缴费的情况
                System.out.println("您没有去收费处缴纳药费，请完成再来！");
            } else if (state.equals("已取药")) {
                // 已批价已缴费已取药的情况下
                System.out.println("您已经取过药了！快回家去好好养病吧！");
            } else if (rs != 0.0) {
                // 已批价已缴费的情况
                System.out.println("正在为您取药..........");
                p.setBuyDrugState("已取药");
                System.out.println("已完成取药，请收好药，回家好好养病吧！");
            } else {
                System.out.println("前面项目都没做完，你就想来拿药想屁吃？？？");
            }
        }
    }

    /**
     * 医生计算药品价格
     */
    public double calcPrice(String str) {
        String[] userDrug = str.split(",");
        double rs = 0.0;
        for (String ud : userDrug) {
            for (Drug drug : drugArr) {
                if (ud.equals(drug.getDrugname())) {
                    rs += drug.getDrugPrice(); // 计算药单价格
                }
            }
        }
        return rs;
    }

    public void docCheckCollection(Patient p, String pro, String drugTxt) {
        String re = doctorCheck(p, pro, drugTxt);
        p.setCheckState(pro + ":" + re);
        String buyState = p.getBuyDrugState(); // 病人买药状态
        if (re.equals("医生已开药") && buyState.equals("无状态")) {
            p.setDrugTxt(drugTxt);
            p.setBuyDrugState("待批价");
        }
    }

    public void aiCheckCollection(Patient p, String pro) {
        String[] c = splitString(p);
        if (c[0].equals(pro)) {
            if (docProCheck(p)) {
                p.setCheckState(pro + ":已检查");
            }
        } else {
            System.out.println("医生没有建议您来检查" + pro + "!!!");
        }
    }
}