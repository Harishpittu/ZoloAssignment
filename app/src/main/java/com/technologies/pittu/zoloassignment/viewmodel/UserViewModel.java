package com.technologies.pittu.zoloassignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

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
        //  creating new object if it is realm object
        this.user = new User();
        this.user.setName(user.getName());
        this.user.setPhoneNumber(user.getPhoneNumber());
        this.user.setEmail(user.getEmail());
        this.user.setPassword(user.getPassword());
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

    private Drawable emailDoneIcon = null;

    public String getUserEmail() {
        return user.getEmail();
    }

    /**
     * set and notify changes
     * @param userEmail string
     */
    public void setUserEmail(String userEmail) {
        this.user.setEmail(userEmail);
        notifyPropertyChanged(R.id.emailEditText);
        notifyPropertyChanged(BR.errorEmail);
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    /**
     * set and notify changes
     * @param userPassword string
     */
    public void setUserPassword(String userPassword) {
        this.user.setPassword(userPassword);
        notifyPropertyChanged(R.id.passwordEditText);
    }

    public String getUserPhoneNumber() {
        return user.getPhoneNumber();
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.user.setPhoneNumber(userPhoneNumber);
        notifyPropertyChanged(R.id.phonenumberEditText);
        notifyPropertyChanged(BR.errorPhoneNumber);
    }

    public String getUserName() {
        return user.getName();
    }

    public void setUserName(String userName) {
        this.user.setName(userName);
        notifyPropertyChanged(R.id.nameEditText);
        notifyPropertyChanged(BR.errorUserName);
    }

    @Bindable
    public Drawable getEmailDoneIcon() {
        return emailDoneIcon;
    }

    public void setEmailDoneIcon(Drawable emailDoneIcon) {
        this.emailDoneIcon = emailDoneIcon;
    }

    /**
     * check for valid password
     *
     * @return boolean
     */
    public boolean isValidPassword() {
        return !(isEmpty(this.user.getPassword()) || this.user.getPassword().length() < 8);
    }

    /**
     * check for valid fields
     *
     * @return boolean
     */
    public boolean isAllFieldsValid() {
        return !(isEmpty(this.user.getPassword()) || this.user.getPassword().length() < 8 || isEmpty(this.user.getEmail()) || !isValidEmail(this.user.getEmail()) ||
                isEmpty(this.user.getName()) || isEmpty(this.user.getPhoneNumber())|| this.user.getPhoneNumber().length() < 8);
    }

    /**
     * check for valid mail
     *
     * @param userEmail email
     * @return bool
     */
    private static boolean isValidEmail(final String userEmail) {
        Pattern pattern;
        Matcher matcher;
        pattern = android.util.Patterns.EMAIL_ADDRESS;
        matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }

    /**
     * bind for error in mail
     *
     * @return boolean
     */
    @Bindable
    public String getErrorEmail() {
        setEmailDoneIcon(ZoloApplication.zoloApplication().getResources().getDrawable(R.drawable.ic_done_green_700_24dp));
        if (this.user.getEmail() == null || !isValidEmail(this.user.getEmail())) {
            return "Enter valid email Id";
        } else {
            return null;
        }
    }


    /**
     * bind for error in phonenumber
     *
     * @return boolean
     */
    @Bindable
    public String getErrorPhoneNumber() {
        if (TextUtils.isEmpty(this.user.getPhoneNumber()) || this.user.getPhoneNumber().length() < 8) {
            return "Please enter valid phonenumber";
        } else {
            return null;
        }
    }


    /**
     * bind for error in name
     *
     * @return boolean
     */
    @Bindable
    public String getErrorUserName() {
        if (TextUtils.isEmpty(this.user.getName())) {
            return "Please enter name";
        } else {
            return null;
        }
    }

    /**
     * checks for empty string
     *
     * @param text string
     * @return bool
     */
    private boolean isEmpty(String text)
    {
        return text == null || text.trim().length() == 0;
    }

}
