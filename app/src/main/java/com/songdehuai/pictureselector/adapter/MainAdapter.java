package com.songdehuai.pictureselector.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.songdehuai.pictureselector.R;
import com.songdehuai.pictureselector.entity.LocalImage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by songdehuai on 2018/2/11.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainImageViewHolder> {
    private Context context;
    private List<LocalImage> localImageList;
    private List<LocalImage> selectImages;

    public MainAdapter(Context context, List<LocalImage> localImageList) {
        this.context = context;
        this.localImageList = localImageList;
        selectImages = new ArrayList<>();
    }

    @Override
    public MainAdapter.MainImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_image, null);
        MainAdapter.MainImageViewHolder viewHolder = new MainImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainImageViewHolder holder, final int position) {
        final LocalImage localImage = localImageList.get(position);
        Glide.with(context).load(localImage.getPath()).into(holder.imageView);
        holder.checkBox.setChecked(localImage.isSelect());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                localImage.setSelect(isChecked);
                if (isChecked) {
                    selectImages.add(localImage);
                } else {
                    selectImages.remove(localImage);
                }
            }
        });
    }

    public List<LocalImage> getSelectImages() {
        return selectImages;
    }

    @Override
    public int getItemCount() {
        return localImageList.size();
    }

    class MainImageViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public ImageView imageView;

        public MainImageViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.item_cb);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }
}
