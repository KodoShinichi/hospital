package com.liuyuanwork.interfacepro;

import com.liuyuanwork.entity.Patient;

/**
 *医院收费接口
 */

public interface ChargePro {

    /**
     * 收费功能
     */
    void toll(Patient p);
}
