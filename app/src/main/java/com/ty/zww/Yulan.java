package com.ty.zww;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;

public class Yulan extends Activity {
        private ImageView s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yulan);
        s = findViewById(R.id.yulan);
        File tu = new File(Environment.getExternalStorageDirectory(),"纸娃娃哎/A1成品");
        s.setImageURI(Uri.fromFile(new File(tu + "/" + ZhiWaWa.wenjian)));
    }
}