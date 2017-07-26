package com.technologies.pittu.zoloassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Copyright (c) on 26/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class Words extends RealmObject{
    @PrimaryKey
    @SerializedName("version")
    @Expose
    public Integer version;
    @SerializedName("words")
    @Expose
    public RealmList<Word> words = new RealmList<>();

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public RealmList<Word> getWords() {
        return words;
    }

    public void setWords(RealmList<Word> words) {
        this.words = words;
    }
}
