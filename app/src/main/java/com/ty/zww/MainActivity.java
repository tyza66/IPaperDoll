package com.ty.zww;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    private static String DB_PATH = Environment.getExternalStorageDirectory().toString();
    private static String DB_NAME = "纸娃娃哎/纸娃娃哎.zip";
    private static final int ASSETS_SUFFIX_BEGIN = 101;
    private static final int ASSETS_SUFFIX_END = 102;
    private static String ASSETS_NAME = "纸娃娃哎/纸娃娃哎.zip";
    private static final int REQUEST_CODE = 1024;
    private Button bt1,bt2,bt3,bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请读写权限
        requestPermission();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, 1);
        //控件
        bt1 = findViewById(R.id.bt_1);
        bt2 = findViewById(R.id.bt_2);
        bt3 = findViewById(R.id.bt_3);
        bt4 = findViewById(R.id.bt_4);
        //点击事件
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, ZhiWaWa.class);
                startActivity(go);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, HuoQu.class);
                startActivity(go);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, JiaoCheng.class);
                startActivity(go);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, GuanYu.class);
                startActivity(go);
            }
        });

        //文件夹事件

        File folder = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎");
        if (!folder.exists()) {
            new Thread(new Runnable(){
            @Override
            public void run(){
            FileUtils1.getInstance(getApplicationContext()).copyAssetsToSD("纸娃娃哎","纸娃娃哎");
            }
        }).start();
            Toast.makeText(MainActivity.this,"第一次安装或刚刚获取读取权限或意图修复文件时请手动重启一次本程序以获得默认素材包",Toast.LENGTH_LONG).show();
        }
        folder = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/B2肢体/默认肢体1.png");
        if (!folder.exists()) {
            new Thread(new Runnable(){
                @Override
                public void run(){
                    FileUtils1.getInstance(getApplicationContext()).copyAssetsToSD("纸娃娃哎","纸娃娃哎");
                }
            }).start();
        }
        folder = new File(Environment.getExternalStorageDirectory(), "纸娃娃哎/G7眼镜/默认眼睛1.png");
        if (!folder.exists()) {
            new Thread(new Runnable(){
                @Override
                public void run(){
                    FileUtils1.getInstance(getApplicationContext()).copyAssetsToSD("纸娃娃哎/G7眼镜","纸娃娃哎/G7眼镜");
                }

            }).start();
        }
    }
    private boolean copyFile(String oldPath,String newPath){
        try{
            int bytesum = 0;
            int byteread = 0;
            String new_e = "";
            File old_e = new File(oldPath);
            if(newPath.endsWith(File.separator)){
                new_e = newPath+old_e.getName();
            }else{
                new_e = newPath+File.separator+old_e.getName();
            }
            new File(newPath).mkdirs();
            try {
                new File(new_e).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(new_e);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
        }
        private byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {
                writeFile();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 先判断有没有权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                writeFile();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            writeFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                writeFile();
            } else {
                Toast.makeText(MainActivity.this,"存储权限获取失败",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                writeFile();
            } else {
                Toast.makeText(MainActivity.this,"存储权限获取失败",Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 模拟文件写入
     */
    private void writeFile() {
        //Toast.makeText(MainActivity.this,"存储权限获取成功",Toast.LENGTH_LONG).show();
    }
}
