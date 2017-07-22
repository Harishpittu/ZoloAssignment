package com.technologies.pittu.zoloassignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.technologies.pittu.zoloassignment.BR;
import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class UserViewModel extends BaseObservable {

    private User user;

    public UserViewModel(User user) {
        this.user = user;
    }

    public UserViewModel() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private boolean isValidPassword = true;
    private Drawable emailDoneIcon = null;
    private Drawable phoneNumberDoneIcon = null;

    public String getUserEmail() {
        return user.getEmail();
    }

    public void setUserEmail(String userEmail) {
        this.user.setEmail(userEmail);
        /** To get value of edittext enterd by user, This Updates the value of userEmail on Every LEtter Entered by User*/
        notifyPropertyChanged(R.id.emailEditText);
        /**to check Email for validation on every character inserted by user*/
        notifyPropertyChanged(BR.errorEmail);
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String userPassword) {
        this.user.setPassword(userPassword);
        // Same for pass word
        notifyPropertyChanged(R.id.passwordEditText);
        //this line will notify new value of password every time
        notifyPropertyChanged(BR.errorPassword);

    }

    public String getUserPhoneNumber() {
        return user.getPhoneNumber();
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.user.setPhoneNumber(userPhoneNumber);
        /** To get value of edittext enterd by user, This Updates the value of userEmail on Every LEtter Entered by User*/
        notifyPropertyChanged(R.id.phonenumberEditText);
        /**to check Email for validation on every character inserted by user*/
        notifyPropertyChanged(BR.errorPhoneNumber);
    }

    public String getUserName() {
        return user.getName();
    }

    public void setUserName(String userName) {
        this.user.setName(userName);
        /** To get value of edittext enterd by user, This Updates the value of userEmail on Every LEtter Entered by User*/
        notifyPropertyChanged(R.id.nameEditText);
        /**to check Email for validation on every character inserted by user*/
        notifyPropertyChanged(BR.errorUserName);
    }

    @Bindable
    public String getErrorPassword() {
        Log.d("", "getErrorPassword: ");
        if (this.user.getPassword() == null || this.user.getPassword().length() < 8) {
            isValidPassword = false;
            return "Enter atleast 8 characters";
        } else {
            isValidPassword = true;
            return null;
        }
    }


    @Bindable
    public Drawable getEmailDoneIcon() {
        return emailDoneIcon;
    }

    public void setEmailDoneIcon(Drawable emailDoneIcon) {
        this.emailDoneIcon = emailDoneIcon;
    }


    public boolean isValidPassword() {
       return !(TextUtils.isEmpty(this.user.getPassword()) || this.user.getPassword().length() < 8);
    }
    public boolean isAllFieldsValid() {
        return !(TextUtils.isEmpty(this.user.getPassword()) || this.user.getPassword().length() < 8 || TextUtils.isEmpty(this.user.getEmail()) || !isValidEmail(this.user.getEmail()) ||
                TextUtils.isEmpty(this.user.getName()) || TextUtils.isEmpty(this.user.getPhoneNumber()));
    }

    //These Methods Check For Validation Every Time user enters a character
    public static boolean isValidEmail(final String userEmail) {
        Pattern pattern;
        Matcher matcher;
        // final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = android.util.Patterns.EMAIL_ADDRESS;
        matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }

    // If you Dont Bind Here You Wont get BR values
    @Bindable
    public String getErrorEmail() {
        setEmailDoneIcon(ZoloApplication.zoloApplication().getResources().getDrawable(R.drawable.ic_done_green_700_24dp));
        if (this.user.getEmail() == null || !isValidEmail(this.user.getEmail())) {
            return "Enter valid email Id";
        } else {
            return null;
        }
    }

    @Bindable
    public String getErrorPhoneNumber() {
        if (TextUtils.isEmpty(this.user.getPhoneNumber()) || this.user.getPhoneNumber().length() < 8) {
            return "Please enter valid phonenumber";
        } else {
            return null;
        }
    }

    @Bindable
    public String getErrorUserName() {
        if (TextUtils.isEmpty(this.user.getName())) {
            return "Please enter name";
        } else {
            return null;
        }
    }

}
