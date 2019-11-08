package com.example.kaoyan.http;

import com.google.gson.annotations.SerializedName;

public class AllInfo {

    public String statue;

    public Basic basic;

    @SerializedName("moreInfo")
    public MoreInfo moreInfo;

    @SerializedName("specialityName")
    public String specialityName;

    public class MoreInfo{

        @SerializedName("Info")
        public Info info;

    }

}
