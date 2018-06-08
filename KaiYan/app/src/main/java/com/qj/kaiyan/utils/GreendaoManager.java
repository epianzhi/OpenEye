package com.qj.kaiyan.utils;

import android.content.Context;

import com.qj.kaiyan.greendao.gen.DaoMaster;

public class GreendaoManager {

    private DaoMaster.DevOpenHelper daoMaster;
    private Context context;

    public GreendaoManager(Context context) {
        this.context=context;
        daoMaster=new DaoMaster.DevOpenHelper(context,"open.db",null);
    }
}
