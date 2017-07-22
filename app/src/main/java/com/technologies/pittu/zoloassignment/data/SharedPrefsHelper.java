package com.technologies.pittu.zoloassignment.data;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

@Singleton
public class SharedPrefsHelper {

    private static String USER_PHONENUMBER = "user_phonenumber";

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * to check is user already logged in
     *
     * @return bool
     */
    public boolean isUserLoggedIn() {
        return sharedPreferences.contains(USER_PHONENUMBER);
    }

    /**
     * to get logged in phone number
     *
     * @return phoneNumber
     */
    public String getLoggedInUserPhoneNumber() {
        return get(USER_PHONENUMBER,"");
    }

    public void put(String key, String value) {
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        this.sharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        this.sharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        this.sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return this.sharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return this.sharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return this.sharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return this.sharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        this.sharedPreferences.edit().remove(key).apply();
    }
}
