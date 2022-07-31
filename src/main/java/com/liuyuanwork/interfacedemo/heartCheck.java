package com.liuyuanwork.interfacedemo;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.index.Patient;

/**
 *心脏科检查实现类
 */

public class heartCheck implements CheckPro{

	/**
	 *心脏科检查方法
	 */
	@Override
	public void aiCheck(){
		Doctor dc = new Doctor();
		Patient p = dc.match();// 匹配挂号
		if(p != null) {
			String[] c = dc.splitString(p);
			if (c[0].equals("心电图")) {
				String cState = dc.docProCheck(p);
				if (cState.equals("已检查")) {
					p.setCheckState("心电图:" + cState);
				}
			} else {
				System.out.println("医生没有建议您来检查心电图！");
			}
		}
	}

	/**
	 *心脏科医生就诊方法
	 */
	@Override
	public void docCheck(Patient p){
		Doctor dc = new Doctor();
		p.setCheckState("心电图:" + dc.doctorCheck(p," 心电图 "));
	}
}
