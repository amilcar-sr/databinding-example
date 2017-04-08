package com.androidsv.databinding.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.androidsv.databinding.R;
import com.androidsv.databinding.model.Item;
import com.bumptech.glide.Glide;

/**
 * Created by amilcar on 4/4/17.
 */

public class ItemViewModel extends BaseObservable {

    private Item mItem;

    public ItemViewModel(Item item) {
        mItem = item;
    }

    public void updateModel(Item item) {
        mItem = item;
        notifyChange();
    }


    //TODO: Remember to mention the getter/setter detail
    public String getId() {
        return mItem.getId();
    }

    @Bindable
    public String getTitle() {
        return mItem.getTitle();
    }

    public void setTitle(String title) {
        mItem.setTitle(title);
    }

    @Bindable
    public String getSubTitle() {
        return mItem.getSubTitle();
    }

    public void setSubTitle(String subTitle) {
        mItem.setSubTitle(subTitle);
    }

    @Bindable
    public String getDescription() {
        return mItem.getDescription();
    }

    public void setDescription(String description) {
        mItem.setDescription(description);
    }

    @Bindable
    public String getImageUrl() {
        return mItem.getImageUrl();
    }

    public void setImageUrl(String imageUrl) {
        mItem.setImageUrl(imageUrl);
    }

    //TODO: Custom is better/safer
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
