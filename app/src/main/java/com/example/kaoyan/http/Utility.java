package com.example.kaoyan.http;

import android.text.TextUtils;

import com.example.kaoyan.db.Information;
import com.example.kaoyan.db.School;
import com.example.kaoyan.db.Speciality;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//显示具体信息
public class Utility {

    //解析和处理服务器返回的学校级数据
    public static boolean handleSchoolResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allSchools = new JSONArray(response);
                for(int i = 0; i<allSchools.length();i++){
                    JSONObject schoolObject = allSchools.getJSONObject(i);
                    School school = new School();
                    school.setSchoolName(schoolObject.getString("name"));
                    school.setSchoolCode(schoolObject.getInt("id"));
                    school.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

//    //解析和处理服务器返回的信息数据
//    public static boolean handleInfomationResponse(String response, int schoolId){
//        if (!TextUtils.isEmpty(response)){
//            try{
//                JSONArray allSchool = new JSONArray(response);
//                for(int i = 0; i<allSchool.length();i++){
//                    JSONObject schoolObject = allSchool.getJSONObject(i);
//                    School school = new School();
//                    school.setSchoolName(schoolObject.getString("name"));
//                    school.setSchoolCode(schoolObject.getInt("id"));
//                    school.save();
//                }
//                return true;
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }

    //解析和处理服务器返回的专业数据
    public static boolean handleSpecialityResponse(String response, int infomationId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allSpeciality = new JSONArray(response);
                for(int i = 0; i<allSpeciality.length();i++){
                    JSONObject SpecialityObject = allSpeciality.getJSONObject(i);
                    Speciality speciality = new Speciality();
                    speciality.setSpecialityName(SpecialityObject.getString("name"));
                    speciality.setSpecialityCode(SpecialityObject.getInt("id"));
                    speciality.setInfomationId(infomationId);
                    speciality.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    //将返回的JSON数据解析成AllInfo实体类
    public  static AllInfo handleAllInfoResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("allInfo");
            String infoContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(infoContent,AllInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
