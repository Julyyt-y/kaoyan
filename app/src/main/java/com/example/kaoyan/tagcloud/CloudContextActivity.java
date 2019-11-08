package com.example.kaoyan.tagcloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kaoyan.R;

public class CloudContextActivity extends AppCompatActivity implements View.OnClickListener{

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_context);

        Button xueXiaoJianJie,kaoShiKeMu, baoLuBi, fuLuBi, tuiMainBiLi,
                jiuYeLv, fenShuXian, kaoShiDaGang,zhuanXueShuo;

        xueXiaoJianJie = findViewById(R.id.xuexiaojianjie);
        kaoShiKeMu = findViewById(R.id.kaoshikemu);
        baoLuBi = findViewById(R.id.baolubi);
        fuLuBi = findViewById(R.id.fulubi);
        tuiMainBiLi = findViewById(R.id.tuimianbili);
        jiuYeLv = findViewById(R.id.jiuyelv);
        fenShuXian = findViewById(R.id.fenshuxian);
        kaoShiDaGang = findViewById(R.id.kaoshidagang);
        zhuanXueShuo = findViewById(R.id.zhuanxueshuo);

        xueXiaoJianJie.setOnClickListener(this);
        kaoShiKeMu.setOnClickListener(this);
        baoLuBi.setOnClickListener(this);
        fuLuBi.setOnClickListener(this);
        tuiMainBiLi.setOnClickListener(this);
        jiuYeLv.setOnClickListener(this);
        fenShuXian.setOnClickListener(this);
        kaoShiDaGang.setOnClickListener(this);
        zhuanXueShuo.setOnClickListener(this);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.xuexiaojianjie:
                Intent intent1 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent1);
                break;
            case R.id.kaoshikemu:
                Intent intent2 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent2);
                break;
            case R.id.baolubi:
                Intent intent3 = new Intent(CloudContextActivity.this,TagItemActivity.class);
                startActivity(intent3);
                break;
            case R.id.fulubi:
                Intent intent4 = new Intent(CloudContextActivity.this,TagItemActivity.class);
                startActivity(intent4);
                break;
            case R.id.tuimianbili:
                Intent intent5 = new Intent(CloudContextActivity.this,TagItemActivity.class);
                startActivity(intent5);
                break;
            case R.id.jiuyelv:
                Intent intent6 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent6);
                break;
            case R.id.fenshuxian:
                Intent intent7 = new Intent(CloudContextActivity.this,TagItemActivity.class);
                startActivity(intent7);
                break;
            case R.id.kaoshidagang:
                Intent intent8 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent8);
                break;
            case R.id.zhuanxueshuo:
                Intent intent9 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent9);
                break;
            default:
                break;
        }
    }

}
