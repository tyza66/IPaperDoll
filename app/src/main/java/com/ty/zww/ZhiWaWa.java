package com.ty.zww;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZhiWaWa extends Activity {
    private Button bt1,bt2,bt3,bt4,bt5,im1,im2,im3,im4,im5,im6,im7,im8;
    private Spinner sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8;
    private ImageView b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    private CheckBox fu1,fu2,fu3,fu4,fu5,fu6,fu7,fu8;
    private TextView t1,dangqian,zhuangtai;
    private String[] y1,y2,y3,y4,y5,y6,y7,y8,y9;
    static String wenjian;
    private int s1 = 1,s2 = 1,s3 = 0,s4 = 0,s5 = 0,s6 = 0,s7 = 0,s8 = 0,giao = 0,yahou = 0;
    private int tuCheng = 2;
    private static final File chengPin = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/A1成品");
    private final File zhiTi= new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/B2肢体");
    private final File touFa = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/C3头发");
    private final File yiFu = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/D4衣服");
    private final File kuZi = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/E5裤子");
    private final File xieZi = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/F6鞋子");
    private final File yanJing = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/G7眼镜");
    private final File shouTao = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/H8手套");
    private final File chiBang = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/I9翅膀");
    private Bitmap bi1,bi2,bi3,bi4,bi5,bi6,bi7,bi8;
    Toast toast = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_wa_wa);
        //Value
        y1 = new String[8];y2 = new String[500];y3 = new String[500];y4 = new String[500];y5 = new String[500];y6 = new String[500];y7 = new String[500];y8 = new String[500];y9 = new String[500];
        bi1 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi2 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi3 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi4 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi5 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi6 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi7 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi8 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        //控件
        sp1 = findViewById(R.id.sp_1);
        sp2 = findViewById(R.id.sp_2);
        sp3 = findViewById(R.id.sp_3);
        sp4 = findViewById(R.id.sp_4);
        sp5 = findViewById(R.id.sp_5);
        sp6 = findViewById(R.id.sp_6);
        sp7 = findViewById(R.id.sp_7);
        sp8 = findViewById(R.id.sp_8);
        b1 = findViewById(R.id.back1);
        b2 = findViewById(R.id.back2);
        b3 = findViewById(R.id.back3);
        b4 = findViewById(R.id.back4);
        b5 = findViewById(R.id.back5);
        b6 = findViewById(R.id.back6);
        b7 = findViewById(R.id.back7);
        b8 = findViewById(R.id.back8);
        b9 = findViewById(R.id.back9);
        b10 = findViewById(R.id.back10);
        fu1 = findViewById(R.id.fu1);
        fu2 = findViewById(R.id.fu2);
        fu3 = findViewById(R.id.fu3);
        fu4 = findViewById(R.id.fu4);
        fu5 = findViewById(R.id.fu5);
        fu6 = findViewById(R.id.fu6);
        fu7 = findViewById(R.id.fu7);
        fu8 = findViewById(R.id.fu8);
        bt2 = findViewById(R.id.butt_1);
        bt3 = findViewById(R.id.butt_2);
        bt4 = findViewById(R.id.butt_3);
        bt5 = findViewById(R.id.butt_4);
        im1 = findViewById(R.id.im_1);
        im2 = findViewById(R.id.im_2);
        im3 = findViewById(R.id.im_3);
        im4 = findViewById(R.id.im_4);
        im5 = findViewById(R.id.im_5);
        im6 = findViewById(R.id.im_6);
        im7 = findViewById(R.id.im_7);
        im8 = findViewById(R.id.im_8);
        t1 = findViewById(R.id.tc_1);
        dangqian = findViewById(R.id.dangqian);
        zhuangtai = findViewById(R.id.zhuangtai);
        //初始化
        ArrayAdapter<String> adapter = getFilesAllName(zhiTi.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp1.setAdapter(adapter);
        adapter = getFilesAllName(touFa.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp2.setAdapter(adapter);
        adapter = getFilesAllName(yiFu.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp3.setAdapter(adapter);
        adapter = getFilesAllName(kuZi.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp4.setAdapter(adapter);
        adapter = getFilesAllName(xieZi.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp5.setAdapter(adapter);
        adapter = getFilesAllName(yanJing.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp6.setAdapter(adapter);
        adapter = getFilesAllName(shouTao.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp7.setAdapter(adapter);
        adapter = getFilesAllName(chiBang.toString());
        if(adapter == null){
            String[] arr = {"(无)"};
            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        }
        sp8.setAdapter(adapter);
        b1.setImageResource(R.drawable.kong);
        b3.setImageURI(Uri.fromFile(new File(zhiTi,"默认肢体1.png")));
        b4.setImageURI(Uri.fromFile(new File(touFa,"默认头发1.png")));
        sp1.setVisibility(View.VISIBLE);
        sp2.setVisibility(View.VISIBLE);

        //复选框事件
        fu1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s1 = 1;
                    if (y1[0] != null)
                    b3.setImageURI(Uri.fromFile(new File(zhiTi,y1[0])));
                    tuCheng++;
                }else{
                    s1 = 0;
                    b3.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s2 = 1;
                    if (y1[1] != null)
                    b4.setImageURI(Uri.fromFile(new File(touFa,y1[1])));
                    tuCheng++;
                }else{
                    s2 = 0;
                    b4.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s3 = 1;
                    if (y1[2] != null)
                    b5.setImageURI(Uri.fromFile(new File(yiFu,y1[2])));
                    tuCheng++;
                }else{
                    s3 = 0;
                    b5.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s4 = 1;
                    if (y1[3] != null)
                        b6.setImageURI(Uri.fromFile(new File(kuZi,y1[3])));
                    tuCheng++;
                }else{
                    s4 = 0;
                    b6.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s5 = 1;
                    if (y1[4] != null)
                        b7.setImageURI(Uri.fromFile(new File(xieZi,y1[4])));
                    tuCheng++;
                }else{
                    s5= 0;
                    b7.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s6 = 1;
                    if (y1[5] != null)
                        b8.setImageURI(Uri.fromFile(new File(yanJing,y1[5])));
                    tuCheng++;
                }else{
                    s6 = 0;
                    b8.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s7 = 1;
                    if (y1[6] != null)
                        b9.setImageURI(Uri.fromFile(new File(shouTao,y1[6])));
                    tuCheng++;
                }else{
                    s7 = 0;
                    b9.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");
                zhuangtai.setText("状态:未构建");
                setTuChengTo();
            }
        });
        fu8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    s8 = 1;
                    if (y1[7] != null)
                        b2.setImageURI(Uri.fromFile(new File(chiBang,y1[7])));
                    tuCheng++;
                }else{
                    s8 = 0;
                    b2.setImageResource(R.drawable.kong);
                    tuCheng--;
                }
                dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");

                setTuChengTo();
            }
        });
        //下拉框事件
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[0] = y2[position];
                if(s1 == 1 && y1[0] != null){
                    b3.setImageURI(Uri.fromFile(new File(zhiTi,y1[0])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[1] = y3[position];
                if(s2 == 1 && y1[1] != null){
                    b4.setImageURI(Uri.fromFile(new File(touFa,y1[1])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[2] = y4[position];
                if(s3 == 1 && y1[2] != null){
                    b5.setImageURI(Uri.fromFile(new File(yiFu,y1[2])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[3] = y5[position];
                if(s4 == 1 && y1[3] != null){
                    b6.setImageURI(Uri.fromFile(new File(kuZi,y1[3])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[4] = y6[position];
                if(s5 == 1 && y1[4] != null){
                    b7.setImageURI(Uri.fromFile(new File(xieZi,y1[4])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[5] = y7[position];
                if(s6 == 1 && y1[5] != null){
                    b8.setImageURI(Uri.fromFile(new File(yanJing,y1[5])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[6] = y8[position];
                if(s7 == 1 && y1[6] != null){
                    b9.setImageURI(Uri.fromFile(new File(shouTao,y1[6])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y1[7] = y9[position];
                if(s8 == 1 && y1[7] != null){
                    b2.setImageURI(Uri.fromFile(new File(chiBang,y1[7])));dangqian.setText("当前:演示图");zhuangtai.setText("状态:未构建");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //按钮点击事件
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = 0;s2 = 0;s3 = 0;s4 = 0;s5 = 0;s6 = 0;s7 = 0;s8 = 0;
                qingChu();
                fu1.setChecked(false);
                fu2.setChecked(false);
                fu3.setChecked(false);
                fu4.setChecked(false);
                fu5.setChecked(false);
                fu6.setChecked(false);
                fu7.setChecked(false);
                fu8.setChecked(false);
                sp1.setVisibility(View.GONE);
                sp2.setVisibility(View.GONE);
                sp3.setVisibility(View.GONE);
                sp4.setVisibility(View.GONE);
                sp5.setVisibility(View.GONE);
                sp6.setVisibility(View.GONE);
                sp7.setVisibility(View.GONE);
                sp8.setVisibility(View.GONE);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s1 == 1){
                    sp1.setSelection((int)(Math.random()*getLongString(y2)));
                }
                if(s2 == 1){
                    sp2.setSelection((int)(Math.random()*getLongString(y3)));
                }
                if(s3 == 1){
                    sp3.setSelection((int)(Math.random()*getLongString(y4)));
                }
                if(s4 == 1){
                    sp4.setSelection((int)(Math.random()*getLongString(y5)));
                }
                if(s5 == 1){
                    sp5.setSelection((int)(Math.random()*getLongString(y6)));
                }
                if(s6 == 1){
                    sp6.setSelection((int)(Math.random()*getLongString(y7)));
                }
                if(s7 == 1){
                    sp7.setSelection((int)(Math.random()*getLongString(y8)));
                }
                if(s8 == 1){
                    sp8.setSelection((int)(Math.random()*getLongString(y9)));
                }
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        zhuangtai.setText("状态:正在构建...");
                        dangqian.setText("当前:构建种...");
                        if(s1 == 1 && getLongString(y2) != 0){
                            bi1 = BitmapFactory.decodeFile(zhiTi+"/"+y1[0]);
                        }
                        if(s2 == 1 && getLongString(y3) != 0){
                            bi2 = BitmapFactory.decodeFile(touFa+"/"+y1[1]);
                        }
                        if(s3 == 1 && getLongString(y4) != 0){
                            bi3 = BitmapFactory.decodeFile(yiFu+"/"+y1[2]);
                        }
                        if(s4 == 1 && getLongString(y5) != 0){
                            bi4 = BitmapFactory.decodeFile(kuZi+"/"+y1[3]);
                        }
                        if(s5 == 1 && getLongString(y6) != 0){
                            bi5 = BitmapFactory.decodeFile(xieZi+"/"+y1[4]);
                        }
                        if(s6 == 1 && getLongString(y7) != 0){
                            bi6 = BitmapFactory.decodeFile(yanJing+"/"+y1[5]);
                        }
                        if(s7 == 1 && getLongString(y8) != 0){
                            bi7 = BitmapFactory.decodeFile(shouTao+"/"+y1[6]);
                        }
                        if(s8 == 1 && getLongString(y9) != 0){
                            bi8 = BitmapFactory.decodeFile(chiBang+"/"+y1[7]);
                        }
                        bulid();
                        zhuangtai.setText("状态:构建完成");
                        dangqian.setText("当前:已保存");

                        try {
                            if(toast!=null){
                                toast.setText("构建成功");
                            }else{
                                toast= Toast.makeText(ZhiWaWa.this, "构建成功:"+wenjian, Toast.LENGTH_SHORT);
                            }
                            toast.show();
                        } catch (Exception e) {
                            //解决在子线程中调用Toast的异常情况处理
                            Looper.prepare();
                            Toast.makeText(ZhiWaWa.this, "构建成功:"+wenjian, Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zhuangtai.getText() == "状态:构建完成"){
                    Intent go = new Intent(ZhiWaWa.this,Yulan.class);
                    startActivity(go);
                }
                else
                {
                    Toast.makeText(ZhiWaWa.this,"请先构建完成后再预览",Toast.LENGTH_SHORT).show();
                }
            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s1 == 1){
                    fu1.setChecked(false);
                    sp1.setVisibility(View.GONE);
                    s1 = 0;
                }else{
                    fu1.setChecked(true);
                    sp1.setVisibility(View.VISIBLE);
                    s1 = 1;
                }

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s2 == 1){
                    fu2.setChecked(false);
                    sp2.setVisibility(View.GONE);
                    s2 = 0;
                }else{
                    fu2.setChecked(true);
                    sp2.setVisibility(View.VISIBLE);
                    s2 = 1;
                }

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s3 == 1){
                    fu3.setChecked(false);
                    sp3.setVisibility(View.GONE);
                    s3 = 0;
                }else{
                    fu3.setChecked(true);
                    sp3.setVisibility(View.VISIBLE);
                    s3 = 1;
                }

            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s4 == 1){
                    fu4.setChecked(false);
                    sp4.setVisibility(View.GONE);
                    s4 = 0;
                }else{
                    fu4.setChecked(true);
                    sp4.setVisibility(View.VISIBLE);
                    s4 = 1;
                }

            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s5 == 1){
                    fu5.setChecked(false);
                    sp5.setVisibility(View.GONE);
                    s5 = 0;
                }else{
                    fu5.setChecked(true);
                    sp5.setVisibility(View.VISIBLE);
                    s5 = 1;
                }

            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s6 == 1){
                    fu6.setChecked(false);
                    sp6.setVisibility(View.GONE);
                    s6 = 0;
                }else{
                    fu6.setChecked(true);
                    sp6.setVisibility(View.VISIBLE);
                    s6 = 1;
                }

            }
        });
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s7 == 1){
                    fu7.setChecked(false);
                    sp7.setVisibility(View.GONE);
                    s7 = 0;
                }else{
                    fu7.setChecked(true);
                    sp7.setVisibility(View.VISIBLE);
                    s7 = 1;
                }

            }
        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s8 == 1){
                    fu8.setChecked(false);
                    sp8.setVisibility(View.GONE);
                    s8 = 0;
                }else{
                    fu8.setChecked(true);
                    sp8.setVisibility(View.VISIBLE);
                    s8 = 1;
                }

            }
        });
    }
    public ArrayAdapter<String> getFilesAllName(String path) {
        int j = 0,biao = 0;
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){Log.e("error","空目录");return null;}
        ArrayAdapter<String> s = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        for(int i =0;i<files.length;i++){
            if (files[i].getAbsolutePath().indexOf("png")!=-1) {
                String[] nei = files[i].getAbsolutePath().split("/");
                s.add(nei[nei.length-1]);
                switch(yahou){
                    case 0:y2[biao++] = nei[nei.length-1];break;
                    case 1:y3[biao++] = nei[nei.length-1];break;
                    case 2:y4[biao++] = nei[nei.length-1];break;
                    case 3:y5[biao++] = nei[nei.length-1];break;
                    case 4:y6[biao++] = nei[nei.length-1];break;
                    case 5:y7[biao++] = nei[nei.length-1];break;
                    case 6:y8[biao++] = nei[nei.length-1];break;
                    case 7:y9[biao++] = nei[nei.length-1];break;
                }
                if (j == 0) y1[giao++] = nei[nei.length-1];
                j = 1;
            }
        }
        yahou++;
        if (j == 0) return null;
        return s;
    }

    private Bitmap compositeImage(Bitmap srcBitmap,Bitmap dstBitmap){
        Bitmap A = Bitmap.createBitmap(128,192,Bitmap.Config.ARGB_8888);
        srcBitmap = scaleBitmap(srcBitmap,128,192);
        dstBitmap = scaleBitmap(dstBitmap,128,192);
        Canvas canvas = new Canvas(A);
        canvas.drawBitmap(srcBitmap, 0, 0,null);
        canvas.drawBitmap(dstBitmap, 0, 0,null);
        return A;
    }

    public static void saveBitmap(Bitmap bitmap){
        File filePic;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            wenjian = (String)df.format(new Date())+".png";
            filePic = new File(chengPin,wenjian);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("xxx", "saveBitmap: 2return");
            return;
        }
        Log.d("xxx", "saveBitmap: " + filePic.getAbsolutePath());
    }
    public void setTuChengTo(){
        switch(tuCheng){
            case 0:t1.setText("图层数:0");break;
            case 1:t1.setText("图层数:1");break;
            case 2:t1.setText("图层数:2");break;
            case 3:t1.setText("图层数:3");break;
            case 4:t1.setText("图层数:4");break;
            case 5:t1.setText("图层数:5");break;
            case 6:t1.setText("图层数:6");break;
            case 7:t1.setText("图层数:7");break;
            case 8:t1.setText("图层数:8");break;
        }
    }
    public void qingChu(){
        b1.setImageResource(R.drawable.kong);
        b2.setImageResource(R.drawable.kong);
        b3.setImageResource(R.drawable.kong);
        b4.setImageResource(R.drawable.kong);
        b5.setImageResource(R.drawable.kong);
        b6.setImageResource(R.drawable.kong);
        b7.setImageResource(R.drawable.kong);
        b8.setImageResource(R.drawable.kong);
        b9.setImageResource(R.drawable.kong);
        b10.setImageResource(R.drawable.kong);
    }
    public int getLongString(String[] a){
        int i = 0;
        while(a[i]!=null)i++;
        return i;
    }
    public void bulid(){
        Bitmap a = compositeImage(bi8,bi1);
        a = compositeImage(a,bi2);
        a = compositeImage(a,bi4);
        a = compositeImage(a,bi5);
        a = compositeImage(a,bi3);
        a = compositeImage(a,bi6);
        a = compositeImage(a,bi7);
        saveBitmap(a);
        bi1 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi2 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi3 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi4 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi5 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi6 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi7 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
        bi8 = BitmapFactory.decodeResource(getResources(),R.drawable.kong);
    }
    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        if(newHeight==height&&newWidth==width){
            return origin;
        }
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
}