package com.liuyuanwork.implementation;


import com.liuyuanwork.entity.Hospital;
import com.liuyuanwork.entity.Patient;
import com.liuyuanwork.interfacepro.ChargePro;

public class Pharmacy implements ChargePro {
    private static final Hospital h = new Hospital();
    /**
     *药方收费方法
     */
    @Override
    public void toll(Patient p) {
        double wait = p.getDrugCalcPrice(); // 药方待付金额
        p.setMedicineMoney(h.ifMoney("药品",wait));
    }
}
