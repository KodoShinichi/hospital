package com.liuyuanwork.implementation;

import com.liuyuanwork.entity.Doctor;
import com.liuyuanwork.entity.Patient;
import com.liuyuanwork.interfacepro.ChargePro;
import com.liuyuanwork.interfacepro.CheckPro;

/**
 * 神经内科实现类
 */

public class Neurology implements CheckPro, ChargePro {
    private static final Doctor dc = new Doctor();
    /**
     * 核磁共振检查方法
     */
    @Override
    public void aiCheck(Patient p) {
        dc.aiCheckCollection(p,"核磁共振");
    }

    /**
     * 神经内科医生就诊
     */
    @Override
    public void docCheck(Patient p) {
        dc.docCheckCollection(p,"核磁共振","阿司匹林,瑞舒伐他汀,单硝酸异山梨酯缓释片");
    }

    /**
     *核磁共振收费方法
     */
    @Override
    public void toll(Patient p) {
        p.setCheckMoney(dc.ifMoney("核磁共振",800.0));
    }
}
