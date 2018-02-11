package com.songdehuai.pictureselector;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.songdehuai.pictureselector.entity.LocalImage;
import com.songdehuai.pictureselector.tools.PictureSelectorNotificationCenter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView main_text;
    Button btn_selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},998);
        main_text = findViewById(R.id.main_text);
        btn_selector = findViewById(R.id.btn_selector);
        btn_selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelectorNotificationCenter.getInstance(getApplicationContext()).startSelectImage(onSelectorListener);
            }
        });
    }

    PictureSelectorNotificationCenter.onSelectorListener onSelectorListener = new PictureSelectorNotificationCenter.onSelectorListener() {
        @Override
        public void onFinish(List<LocalImage> localImageList) {
            for (int i = 0; i < localImageList.size(); i++) {
                main_text.append(localImageList.get(i).getPath());
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "取消选择", Toast.LENGTH_SHORT).show();
        }
    };
}
