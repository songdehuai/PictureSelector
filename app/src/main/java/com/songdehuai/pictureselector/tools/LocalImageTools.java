package com.songdehuai.pictureselector.tools;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.songdehuai.pictureselector.entity.LocalImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songdehuai on 2018/2/11.
 */

public class LocalImageTools {

    private static LocalImageTools localImageTools;
    private Context context;
    private List<LocalImage> localImages;

    public LocalImageTools(Context context) {
        this.context = context;
    }

    public static LocalImageTools init(Context context) {
        if (localImageTools == null) {
            localImageTools = new LocalImageTools(context);
        }
        return localImageTools;
    }


    public List<LocalImage> getAll() {
        localImages = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取图片的名称
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            //获取图片的生成日期
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            //获取图片的详细信息
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            //获取图片地址
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            LocalImage localImage = new LocalImage();
            localImage.setName(name);
            localImage.setDesc(desc);
            localImage.setPath(path);
            localImages.add(localImage);
        }
        return localImages;
    }
}
