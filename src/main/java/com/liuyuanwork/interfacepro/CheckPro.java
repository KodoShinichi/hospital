package com.liuyuanwork.interfacepro;
import com.liuyuanwork.entity.Patient;

/**
 *医院检查接口
 */

public interface CheckPro{

	/**
	 * 项目检查接口
	 */

	void aiCheck(Patient p);

	/**
	 * 各医生检查接口
	 */
	void docCheck(Patient p);
}