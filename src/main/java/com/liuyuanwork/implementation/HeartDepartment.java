package com.liuyuanwork.implementation;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.entity.Patient;
import com.liuyuanwork.interfacepro.ChargePro;
import com.liuyuanwork.interfacepro.CheckPro;

/**
 * 心脏科实现类
 */

public class HeartDepartment implements CheckPro, ChargePro {
    private static final Doctor dc = new Doctor();
    /**
     * 心脏科检查方法
     */
    @Override
    public void aiCheck(Patient p) {
        dc.aiCheckCollection(p,"心电图");
    }

    /**
     * 心脏科医生就诊方法
     */
    @Override
    public void docCheck(Patient p) {
        dc.docCheckCollection(p,"心电图","阿司匹林,瑞舒伐他汀,单硝酸异山梨酯缓释片");
    }

    /**
     * 心电图收费方法
     */
    @Override
    public void toll(Patient p) {
        p.setCheckMoney(dc.ifMoney("心电图", 100.0));
    }
}
