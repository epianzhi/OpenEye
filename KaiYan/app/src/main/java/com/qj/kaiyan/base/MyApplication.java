package com.qj.kaiyan.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.qj.kaiyan.greendao.gen.DaoMaster;
import com.qj.kaiyan.greendao.gen.DaoSession;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

import javax.xml.transform.sax.TemplatesHandler;

public class MyApplication extends Application {

    public static  MyApplication context;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private static DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();

        context=this;
//        UMShareAPI.init(this);
        UMConfigure.init(this,"59784a4476661332dc0006d1",null,UMConfigure.DEVICE_TYPE_PHONE,"");

        PlatformConfig.setWeixin("wxac995e7a84d8a0b5","10740acf9080574b3a1a6b8dee41f5e0");
        PlatformConfig.setQQZone("1106237451", "dSwsZZBBsajUlxwh");

        setUpGreendao();
    }

    private void setUpGreendao() {

        devOpenHelper = new DaoMaster.DevOpenHelper(this,"openeye.db",null);
        db=devOpenHelper.getWritableDatabase();
        daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
    }

    public static Context getContext() {
        return context;
    }

    public  static DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
