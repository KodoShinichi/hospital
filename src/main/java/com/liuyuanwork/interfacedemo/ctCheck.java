package com.liuyuanwork.interfacedemo;
import com.liuyuanwork.entity.Hospital;

/**
 *消化科实现类
 */

public class ctCheck extends Hospital implements CheckPro{
	/**
	 *CT检查方法
	 */
	@Override
	public void aiCheck(){
		Hospital hp = match();// 匹配挂号
		if (hp != null) {
			double give = hp.getCtMoney(); // 获取用户CT的缴费情况
			String state = hp.getCtState(); // 获取当前客户ct检查状态
			if(give == 0.0 && "待检查".equals(state)){
				// 没交费待检查的情况
				System.out.println("\n您没有缴费，请缴费后再过来检查！");
			}else if(give != 0.0 && "待检查".equals(state)){
				// 缴费了待检查的情况
				System.out.println("\n开始检查............");
				System.out.println("\n....................");
				System.out.println("\n检查结束！请回到医生处开具药方");
				hp.setCtState("已检查");
			}else if(give != 0.0 && "已检查".equals(state)){
				// 缴费了 已检查的情况
				System.out.println("\n您已经检查过了，无需再次检查！");
			}else if("已开药".equals(state)){
				// 已经开药的情况
				System.out.println("您都开药了跑过来干嘛！滚去药房！");
			}else {
				// 没缴费 没有检查需求的情况
				System.out.println("\n医生没有建议您来检查此项目，会死的！");
			}
		}else {
			System.out.println("\n您未挂号，请挂号后再过来！");
		}
	}

	/**
	 *消化内科医生就诊
	 */
	@Override
	public void docCheck(Hospital hp){
		String state = hp.getCtState(); // 获取当前客户ct检查状态
		if(state == null){
			System.out.println("\n望闻问切...");
			System.out.println("\n...........");
			System.out.println("\n经过我的问诊我建议您去缴费做CT检查后拿结果来我这开药");
			hp.setCtState("待检查"); // 问诊完修改检查状态为待检查
		}else if("待检查".equals(state)) {
			System.out.println("\n请您检查完CT后再过来！");
		}else if("已检查".equals(state)){
			System.out.println("\n即将为您开药！请耐心等待...");
			hp.setDrugOrders("奥美拉唑,雷贝拉唑,阿莫西林");
			System.out.println("您的药方："+ hp.getDrugOrders() + "请到药房批价。");
			hp.setCtState("已开药"); // 开完药修改检查状态为已开药
			hp.setBuyDrugState("待批价");
		}else if("已开药".equals(state)){
			System.out.println("您已检查并开药请去药房批价后取药吧！");
		}
	}
}