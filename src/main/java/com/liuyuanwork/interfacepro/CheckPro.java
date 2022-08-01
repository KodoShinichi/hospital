package com.liuyuanwork.interfacepro;
import com.liuyuanwork.entity.Patient;

/**
 *医院检查接口
 */

public interface CheckPro{

	/**
	 * 项目检查方法原型
	 */

	void aiCheck(Patient p);

	/**
	 * 各医生检查方法原型
	 */
	void docCheck(Patient p);
}