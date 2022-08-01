package com.liuyuanwork.customexception;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 异常捕捉类
 */
public class ExceptionCatch {
    Scanner sc = new Scanner(System.in);

    /**
     * 判断输入是否正确
     */
    public int chooseOpt(String[] arr){
        System.out.print("请选择您需要的功能：");
        while (true){
            try {
                int opt = sc.nextInt();
                if (opt >0 && opt<=arr.length) {
                    return opt;
                }else {
                    System.out.print("\n您的选择有误请重新输入：");
                }
            } catch (Exception e) {
                System.out.print("\n输入不合法请重新输入：");
                sc.next();
            }
        }
    }

    /**
     * 性别输入捕捉异常方法
     */
    public String catchGenderEx(){
        while (true){
            try {
                String name = sc.next();
                if(name.equals("男") || name.equals("女")){
                    return name;
                }else {
                    throw new GenderException();
                }
            } catch (GenderException e) {
                System.out.print("您输入的有误，请输入男或者女：");
            }

        }
    }

    /**
     * int类型输入捕捉异常方法
     */
    public int catchIntEx(){
        while (true){
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("您输入的不是数字请重新输入：");
                sc.next();
            }

        }
    }

    /**
     * Long类型输入捕捉异常方法
     */
    public Long catchLongEx(){
        while (true){
            try {
                return sc.nextLong();
            } catch (InputMismatchException e) {
                System.out.print("您输入的不是数字请重新输入：");
                sc.next();
            }

        }
    }
}
