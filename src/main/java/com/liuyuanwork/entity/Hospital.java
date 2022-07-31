package com.liuyuanwork.entity;

import com.liuyuanwork.index.Patient;
import com.liuyuanwork.interfacedemo.CheckPro;
import com.liuyuanwork.interfacedemo.ctCheck;
import com.liuyuanwork.interfacedemo.heartCheck;
import com.liuyuanwork.interfacedemo.nmrCheck;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *医院父类(Hospital)
 * @author 刘芫
 * @since jdk1.8
 * @version 2.0
 */

public class Hospital{

    ArrayList<Patient> list = new ArrayList<>();

    public void choseDoc(CheckPro cp, Patient p) {
        cp.docCheck(p);
    }
    public void choseAi(CheckPro cp) {
        cp.aiCheck();
    }

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

    // 创建对象
    ctCheck ctDoc = new ctCheck(); // 消化科实现类对象
    nmrCheck nmrDoc = new nmrCheck(); // 神经内科实现类对象
    heartCheck htDoc = new heartCheck(); // 心脏科实现类对象

    /**
     * 判断输入是否正确
     */
    Scanner input = new Scanner(System.in);
    public int chooseOpt(String[] arr){
        System.out.print("请选择您需要的功能：");
        while (true){
            try {
                int opt = input.nextInt();
                if (opt >0 && opt<=arr.length) {
                    return opt;
                }else {
                    System.out.print("\n您的选择有误请重新输入：");
                }
            } catch (Exception e) {
                System.out.print("\n输入不合法请重新输入：");
                input.next();
            }
        }
    }

    /**
     *匹配是否挂号方法
     */
    public Patient match(){
        System.out.print("请您输入您的挂号号码：");
        int id = input.nextInt();
        for (int i=0; i<list.size() && list.get(i) != null; i++){
            if(id == list.get(i).getId()){
                return list.get(i);
            }
        }
        System.out.println("您未挂号，请挂号后再过来！");
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
            return 0.0;
        }
    }
    /**
     * 分配就诊医生
     */
    public void makePrescription() {
        Patient p = match();// 匹配挂号
        if (p != null) {
            switch (p.getDepartment()) {
                case "消化科":
                    choseDoc(ctDoc,p);
                    break;
                case "神经内科":
                    choseDoc(nmrDoc,p);
                    break;
                case "心脏科":
                    choseDoc(htDoc,p);
                    break;
                default:
            }
        }
    }

    /**
     * 分配项目检查医生
     */
    public void makeAiDocCheck(int secondOpt){
        switch (secondOpt) {
            case 1:
                choseAi(ctDoc);
                break;
            case 2:
                choseAi(nmrDoc);
                break;
            case 3:
                choseAi(htDoc);
                break;
            case 4:
                break;
        }
    }

    /**
     * 分割状态字符串
     */
    public String[] splitString(Patient p){
        String state = p.getCheckState();
        return state.split(":");
    }
}