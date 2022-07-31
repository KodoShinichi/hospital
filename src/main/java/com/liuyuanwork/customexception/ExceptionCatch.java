package com.liuyuanwork.customexception;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 异常捕捉类
 */
public class ExceptionCatch {
    Scanner sc = new Scanner(System.in);

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
     * 年龄输入捕捉异常方法
     */
    public int catchAgeEx(){
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
     * 手机号输入捕捉异常方法
     */
    public Long catchTeleEx(){
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
