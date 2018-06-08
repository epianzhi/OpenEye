package com.qj.kaiyan.entitys;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

public class CoverCoverter implements PropertyConverter<HomeResultItem.CoverBean,String> {
    @Override
    public HomeResultItem.CoverBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        return new Gson().fromJson(databaseValue, HomeResultItem.CoverBean.class);
    }

    @Override
    public String convertToDatabaseValue(HomeResultItem.CoverBean entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}
