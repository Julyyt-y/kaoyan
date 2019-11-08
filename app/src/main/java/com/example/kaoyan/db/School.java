package com.example.kaoyan.db;

import org.litepal.crud.DataSupport;

public class School extends DataSupport {

    private int id; //编号

    private String schoolName;  //学校名字

    private int schoolCode;  //学校代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(int schoolCode) {
        this.schoolCode = schoolCode;
    }
}
