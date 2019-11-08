package com.example.kaoyan.tagcloud;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaoyan.R;
import com.example.kaoyan.db.Information;
import com.example.kaoyan.db.School;
import com.example.kaoyan.db.Speciality;
import com.example.kaoyan.http.HttpUtil;
import com.example.kaoyan.http.AllInfo;
import com.example.kaoyan.http.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.litepal.LitePalApplication.getContext;

public class TagItemActivity extends AppCompatActivity {

    public static final int LEVEL_SCHOOL = 0;

    public static final int LEVEL_INFORMATION = 1;

    public static final int LEVEL_Speciality = 2;

    private TextView titleView;

    private Button backButton;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    /**
     * 学校列表
     */
    public static List<School> schoolList;

    /**
     * 专业列表
     */
    private List<Speciality> specialityList;

    /**
     * 信息列表
     */
    private List<Information> informationList;

    //选中的学校
    private School selectedSchool;

    //选中的信息
    private Information selectedInfomation;

    //当前选中的级别
    private int currentLevel;

    private List<Item> list = new ArrayList<>();    //专业信息

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

//        initItem();
        ItemAdapter adapter = new ItemAdapter(TagItemActivity.this,R.layout.list_item,list);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String infoString = prefs.getString("info",null);
        if (infoString!=null){
            //有缓存时直接解析数据
            AllInfo allInfo = Utility.handleAllInfoResponse(infoString);
//            initItem(allInfo);
            showAllInfo();  //显示信息
        }else {
            //无缓存时去服务器查询天气
            String itemId = getIntent().getStringExtra("itemId");
            requestInfo(itemId);
        }

//        //发起网络请求
//        HttpUtil.sendOkHttpRequest("http://127.0.0.1:8000/info/?id=20&word=jieshao&count=0\n",new okhttp3.Callback(){
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //得到服务器返回的具体数据
//
////                String responseData = response.body().string();
////                showResponse(responseData);
////                Log.d(responseData,"12345");
//
//            }
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //进行异常处理
//            }
//        });
    }

//    private void initItem(AllInfo name){
//
//        String specialityName = name.specialityName;
//        String title = name.specialityName;
////        String titleItem = name.;
////        String updateTime = name.basic.update.updateTime.split(" ")[1];
////
////        for(int i = 0; i < specialityList.size(); i++){
////            for (int j = 0; j < 2; j++){
////                Item item1 = new Item(title,titleItem);
////                list.add(item1);
////            }
////        }
//    }

    //根据Info的id请求IInformation
    public void requestInfo(final String infoId){
        String infoUrl = "http://127.0.0.1:8000/info/?school=all";

//                + infoId + "";////
        HttpUtil.sendOkHttpRequest(infoUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TagItemActivity.this,"获取信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final AllInfo allInfo = Utility.handleAllInfoResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(allInfo!=null&&"ok".equals(allInfo.statue)){
                            Log.d("return", responseText);
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(TagItemActivity.this).edit();
                            editor.putString("info",responseText);
                            editor.apply();
//                            initItem(allInfo);
                            showAllInfo();  //显示信息
                        }else {
                            Toast.makeText(TagItemActivity.this,"获取信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //判断从服务器传回来的文件类型
    private void type(Response type){

    }

    //查询所有学校
    private void querySchool(){
        schoolList = DataSupport.findAll(School.class);
        if (schoolList.size() > 0){
            dataList.clear();
            for(School school : schoolList){
                dataList.add(school.getSchoolName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_SCHOOL;
        }else {
            String address = "http://127.0.0.1:8000/info/?id=all";
            queryFromServer(address,"school");
        }
    }

//    //查询所有信息
//    private void queryInformation(){
//        informationList = DataSupport.where("schoolId=?",String.valueOf(selectedSchool.getId())).find(Information.class);
//        if (informationList.size() > 0){
//            dataList.clear();
//            for(Information information : informationList){
//                dataList.add(information.getSchoolId());
//            }
//            adapter.notifyDataSetChanged();
//            listView.setSelection(0);
//            currentLevel = LEVEL_INFORMATION;
//        }else {
//            int informationCode = selectedSchool.getSchoolCode();
//            String address = ""+informationCode;////
//            queryFromServer(address,"information");
//        }
//    }

    //查询所有专业
    private void querySpeciality(){
        specialityList = DataSupport.where("informationId=?",String.valueOf(selectedInfomation.getId())).find(Speciality.class);
        if (specialityList.size() > 0){
            dataList.clear();
            for(Speciality speciality : specialityList){
                dataList.add(speciality.getSpecialityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_Speciality;
        }else {
            int specialityCode = selectedSchool.getSchoolCode();
            String address = ""+specialityCode;////
            queryFromServer(address,"speciality");
        }
    }

    //跟据传入的地址和类型在服务器上查询
    private void queryFromServer(String address, final String type){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //通过方法返回到主线程
                TagItemActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;
                if ("school".equals(type)){
                    result = Utility.handleSchoolResponse(responseText);
                }
//                else if ("information".equals(type)){
//                    result = Utility.handleInfomationResponse(responseText,selectedSchool.getId());
//                }
                else if ("Speciality".equals(type)){
                    result = Utility.handleSpecialityResponse(responseText,selectedInfomation.getId());
                }
                if (result){
                    TagItemActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ("school".equals(type)){
                                querySchool();
                            }
//                            else if ("information".equals(type)){
//                                queryInformation();
//                            }
                            else if ("speciality".equals(type)){
                                querySpeciality();
                            }
                        }
                    });
                }
            }
        });
    }

    public void showAllInfo(){

    }

}
