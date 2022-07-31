package com.liuyuanwork.interfacedemo;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.index.Patient;


/**
 *神经内科实现类
 */

public class nmrCheck implements CheckPro{
	/**
	 *核磁共振检查方法
	 */
	@Override
	public void aiCheck(){
		Doctor dc = new Doctor();
		Patient p = dc.match();// 匹配挂号
		if(p != null) {
			String[] c = dc.splitString(p); // 字符分割方法
			if (c[0].equals("核磁共振")) {
				String cState = dc.docProCheck(p);
				if (cState.equals("已检查")) {
					p.setCheckState("核磁共振:" + cState);
				}
			} else {
				System.out.println("医生没有建议您来检查核磁共振！");
			}
		}
	}

	/**
	 *神经内科医生就诊
	 */
	@Override
	public void docCheck(Patient p){
		Doctor dc = new Doctor();
		p.setCheckState("核磁共振:" + dc.doctorCheck(p," 核磁共振 "));
	}
}
