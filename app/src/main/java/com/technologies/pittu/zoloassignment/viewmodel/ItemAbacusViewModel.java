

package com.technologies.pittu.zoloassignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.model.Word;


public class ItemAbacusViewModel extends BaseObservable {

    private Word word;

    public ItemAbacusViewModel(Word word) {
        this.word = word;
    }

    public String getId() {
        return word.getId().toString();
    }

    public String getWord() {
        return word.getWord();
    }

    public String getVariant() {
        return word.getVariant().toString();
    }

    public String getMeaning() {
        return word.getMeaning();
    }

    public String getRatio() {
        return word.getRatio().toString();
    }

    public String getImageUrl() {
        return "http://appsculture.com/vocab/images/" + word.id + ".png";
    }

    public void setWord(Word word) {
        this.word = word;
        notifyChange();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
    }

}
