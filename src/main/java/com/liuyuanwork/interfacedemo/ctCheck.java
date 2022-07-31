package com.liuyuanwork.interfacedemo;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.entity.Hospital;
import com.liuyuanwork.index.Patient;

/**
 *消化科实现类
 */

public class ctCheck implements CheckPro{
	/**
	 *CT检查方法
	 */
	@Override
	public void aiCheck(){
		Doctor dc = new Doctor();
		Patient p = dc.match();// 匹配挂号
		if(p != null){
			String[] c = dc.splitString(p);
			if(c[0].equals("CT")){
				String cState = dc.docProCheck(p);
				if(cState.equals("已检查")){
					p.setCheckState("CT:" + cState);
				}
			}else {
				System.out.println("医生没有建议您来检查CT！");
			}
		}

	}

	/**
	 *消化内科医生就诊
	 */
	@Override
	public void docCheck(Patient p){
		Doctor dc = new Doctor();
		p.setCheckState("CT:" + dc.doctorCheck(p," CT "));
		int i = Hospital.drugArr.length;
	}
}