package com.liuyuanwork.interfacedemo;
import com.liuyuanwork.entity.Hospital;
/**
 *医院检查接口类
 */

public interface CheckPro{
	// 项目检查接口
	void aiCheck();
	// 各医生检查接口
	void docCheck(Hospital hp);
}