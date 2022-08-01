package com.liuyuanwork.implementation;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.entity.Patient;
import com.liuyuanwork.interfacepro.ChargePro;
import com.liuyuanwork.interfacepro.CheckPro;

/**
 *消化科实现类
 */

public class GastroDepartment implements CheckPro, ChargePro {
	private static final Doctor dc = new Doctor();
	/**
	 *CT检查方法
	 */
	@Override
	public void aiCheck(Patient p){
		dc.aiCheckCollection(p,"CT");
	}

	/**
	 *消化内科医生就诊
	 */
	@Override
	public void docCheck(Patient p){
		dc.docCheckCollection(p,"CT","奥美拉唑,雷贝拉唑,阿莫西林");
	}

	/**
	 *CT收费方法
	 */
	@Override
	public void toll(Patient p) {
		p.setCheckMoney(dc.ifMoney("CT",200.0));
	}
}