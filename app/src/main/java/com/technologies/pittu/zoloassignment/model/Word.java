package com.technologies.pittu.zoloassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Copyright (c) on 26/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class Word extends RealmObject {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("word")
    @Expose
    public String word;
    @SerializedName("variant")
    @Expose
    public Integer variant;
    @SerializedName("meaning")
    @Expose
    public String meaning;
    @SerializedName("ratio")
    @Expose
    public Float ratio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getVariant() {
        return variant;
    }

    public void setVariant(Integer variant) {
        this.variant = variant;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }
}
