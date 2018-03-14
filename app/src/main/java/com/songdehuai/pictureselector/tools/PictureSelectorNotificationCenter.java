package com.songdehuai.pictureselector.tools;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;

import com.songdehuai.pictureselector.PictureSelectorActivity;
import com.songdehuai.pictureselector.entity.LocalImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:选择图片观察者
 *
 * @author songdehuai
 * @ClassName: com.songdehuai.pictureselector.tools.PictureSelectorNotificationCenter
 * @date 2018/2/11 20:50
 */
public class PictureSelectorNotificationCenter {


    public static final String SELECT_IMAGE = "SELECT_IMAGE";
    private Context context;
    private static PictureSelectorNotificationCenter notificationCenter = null;
    private onSelectorListener onSelectorListener;

    private PictureSelectorNotificationCenter() {
    }

    public PictureSelectorNotificationCenter(Context context) {
        this.context = context;
    }

    public static PictureSelectorNotificationCenter getInstance() {
        if (notificationCenter == null) {
            notificationCenter = new PictureSelectorNotificationCenter();
        }
        return notificationCenter;
    }
    public static PictureSelectorNotificationCenter getInstance(Context context) {
        if (notificationCenter == null) {
            notificationCenter = new PictureSelectorNotificationCenter(context);
        }
        return notificationCenter;
    }

    public void startSelectImage(onSelectorListener onSelectorListener) {
        Intent intent = new Intent(context, PictureSelectorActivity.class);
        context.startActivity(intent);
        this.onSelectorListener = onSelectorListener;
    }


    /**
     * 回调
     *
     * @param localImages
     */
    public void postImage(List<LocalImage> localImages) {
        onSelectorListener.onFinish(localImages);
    }

    public void cancel() {
        onSelectorListener.onCancel();
    }


    public interface onSelectorListener {

        void onFinish(List<LocalImage> localImageList);

        void onCancel();
    }

}
