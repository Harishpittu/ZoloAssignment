package com.technologies.pittu.zoloassignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.BR;
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
    private Drawable doneIcon;

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

    @Bindable
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

    @Bindable
    public String getUserPhoneNumber() {
        return user.getPhoneNumber();
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.user.setPhoneNumber(userPhoneNumber);
    }
    @Bindable
    public String getUserName() {
        return user.getPhoneNumber();
    }

    public void setUserName(String userName) {
        this.user.setName(userName);
    }

    //These Methods Check For Validation Every Time user enters a character
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);

        matcher = pattern.matcher(password);
        return matcher.matches();
    }


    @Bindable
    public String getErrorPassword() {
        Log.d("", "getErrorPassword: ");
        if (this.user.getPassword() == null) {
            isValidPassword = false;
            setDoneIcon(null);
            return "Please Enter";
        } else if (this.user.getPassword().length() < 8) {
            isValidPassword = false;
            setDoneIcon(null);
            return "Enter atleast 8 characters";
        } else {
//            if (this.user.getPassword().length() < 10)
//                setDoneIcon(ZoloApplication.zoloApplication().getResources().getDrawable(R.drawable.ic_done_black_24dp));
//            else
//                setDoneIcon(App.app().getResources().getDrawable(R.drawable.ic_visibility_white_24dp));
            isValidPassword = true;
            return null;
        }

    }

    @Bindable
    public String getErrorPhoneNumber() {

        return "phone num";
    }

    @Bindable
    public Drawable getDoneIcon() {
//        Log.d("", "getDoneIcon: ");
//        if (isValidPassword) {
//        } else {
//            return null;
//        }
        return doneIcon;
    }

    public void setDoneIcon(Drawable doneIcon) {
        this.doneIcon = doneIcon;
        notifyPropertyChanged(BR.doneIcon);
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
        if (this.user.getEmail() == null) {
            return "Please Enter";
        } else if (!isValidEmail(this.user.getEmail())) {
            return "Enter valid Id";
        } else {
            return null;
        }
    }

}
