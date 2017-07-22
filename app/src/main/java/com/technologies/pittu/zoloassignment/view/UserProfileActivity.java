package com.technologies.pittu.zoloassignment.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;
import com.technologies.pittu.zoloassignment.data.SharedPrefsHelper;
import com.technologies.pittu.zoloassignment.databinding.ProfileDataBinding;
import com.technologies.pittu.zoloassignment.model.User;
import com.technologies.pittu.zoloassignment.utils.Utils;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import javax.inject.Inject;


/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class UserProfileActivity extends AppCompatActivity {

    @Inject
    SharedPrefsHelper sharedPrefsHelper;
    @Inject
    RealmDatabaseHelper realmDatabaseHelper;

    ProfileDataBinding profileDataBinding;
    private boolean isPasswordVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ZoloApplication.getApplicationComponent().inject(this);
        profileDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        User user = realmDatabaseHelper.getUserWithPhoneNumber(sharedPrefsHelper.getLoggedInUserPhoneNumber());
        UserViewModel viewModel = new UserViewModel(user);
        profileDataBinding.setProfile(viewModel);
        profileDataBinding.setPasswordInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        profileDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_white_24dp);
    }

    /**
     * on click of login button
     *
     * @param view view
     */
    public void onClickLogout(View view) {
        sharedPrefsHelper.clearAllData();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * on click of register
     *
     * @param view view
     */
    public void onClickProfile(View view) {
        if (!profileDataBinding.getProfile().isValidPassword()) {
            Utils.showSnackBar("Password should have minimum 8 characters", view);
        } else if (!profileDataBinding.getProfile().isAllFieldsValid()) {
            Utils.showSnackBar("Please enter all fields", view);
        } else {
            realmDatabaseHelper.copyORUpdate(profileDataBinding.getProfile().getUser());
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Success")
                    .setMessage("Your Profile Successfully updated")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
        }
    }

    /**
     * on click of show icon
     *
     * @param view view
     */
    public void onClickShowIcon(View view) {
        if (isPasswordVisible) {
            isPasswordVisible = false;
            profileDataBinding.setPasswordInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            profileDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_white_24dp);
        } else {
            isPasswordVisible = true;
            profileDataBinding.setPasswordInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            profileDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_off_white_24dp);
        }

    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
