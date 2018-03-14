package com.songdehuai.pictureselector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.songdehuai.pictureselector.adapter.MainAdapter;
import com.songdehuai.pictureselector.entity.LocalImage;
import com.songdehuai.pictureselector.tools.LocalImageTools;
import com.songdehuai.pictureselector.tools.PictureSelectorNotificationCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songdehuai on 2018/2/11.
 */

public class PictureSelectorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    Button btn_finish;
    Button btn_cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        init();
    }

    List<LocalImage> localImageList;

    private void init() {
        recyclerView = findViewById(R.id.pic_rc);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_finish = findViewById(R.id.btn_finish);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        localImageList = new ArrayList<>();
        localImageList = LocalImageTools.init(getApplicationContext()).getAll();
        mainAdapter = new MainAdapter(getApplicationContext(), localImageList);
        mainAdapter.setMaxCount(9);
        recyclerView.setAdapter(mainAdapter);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelectorNotificationCenter.getInstance().postImage(mainAdapter.getSelectImages());
                finish();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelectorNotificationCenter.getInstance().cancel();
                finish();
            }
        });

    }

}
