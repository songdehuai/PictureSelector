package com.songdehuai.pictureselector.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.songdehuai.pictureselector.entity.LocalImage;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：图片选择器
 *
 * @author songdehuai
 * @ClassName: com.songdehuai.pictureselector.view.
 * @date 2018/3/6 16:49
 */

public class ImagePicker extends LinearLayout {

    private List<LocalImage> localImages;
    private int column = 3;
    private int imageWidth = 0;
    private int imageHeight = 100;

    int width = 1080;
    int height = 1920;

    public ImagePicker(Context context) {
        super(context);
        initViews();
    }

    public ImagePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public ImagePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        if (localImages == null) {
            Toast.makeText(getContext(), "图片列表是空的!", Toast.LENGTH_SHORT).show();
            return;
        }
        clearImageView();
        setOrientation(LinearLayout.VERTICAL);
        setImageHeight(100);
        setImageWidth();

        LinearLayout imageViewLayout = new LinearLayout(getContext());
        imageViewLayout.setOrientation(LinearLayout.HORIZONTAL);
        imageViewLayout.setWeightSum(column);
        for (int i = 0; i < localImages.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LayoutParams layoutParams = new LayoutParams(imageWidth, imageHeight, 1);
            imageView.setLayoutParams(layoutParams);
            Glide.with(getContext().getApplicationContext()).load(localImages.get(i).getPath()).into(imageView);
            imageViewLayout.addView(imageView);

            if (i <= 3) {
                addView(imageViewLayout);
                return;
            }
            if ((column % 3) == 0) {
                addView(imageViewLayout);
            }
        }

    }


    public void setImageHeight(int imageHeight) {
        this.imageHeight = dip2px(getContext(), imageHeight);
    }

    public void setImageWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;
        this.imageWidth = dip2px(getContext(), width / column);

    }


    /**
     * 获得列数
     *
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * 设置列数
     *
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
        if (localImages != null) {
            initViews();
        }
    }

    public void setLocalImages(List<LocalImage> localImages) {
        if (this.localImages != null) {
            this.localImages.clear();
        }
        this.localImages = localImages;
        initViews();
    }

    void clearImageView() {
        if (localImages != null) {
            removeAllViews();
        }
    }

    public List<LocalImage> getLocalImages() {
        return localImages;
    }

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
