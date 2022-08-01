package com.liuyuanwork.implementation;


import com.liuyuanwork.entity.Hospital;
import com.liuyuanwork.entity.Patient;
import com.liuyuanwork.interfacepro.ChargePro;

/**
 *药房实现类
 */

public class DrugDepartment implements ChargePro {

    private static final Hospital h = new Hospital();
    @Override
    public void toll(Patient p) {
        double wait = p.getDrugCalcPrice(); // 药方待付金额
        p.setMedicineMoney(h.ifMoney("药品",wait));
    }
}
