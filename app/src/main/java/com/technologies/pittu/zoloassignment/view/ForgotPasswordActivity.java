package com.technologies.pittu.zoloassignment.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;
import com.technologies.pittu.zoloassignment.databinding.ForgotPasswordDataBinding;
import com.technologies.pittu.zoloassignment.model.User;
import com.technologies.pittu.zoloassignment.utils.Utils;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import javax.inject.Inject;


/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    @Inject
    RealmDatabaseHelper realmDatabaseHelper;
    ForgotPasswordDataBinding forgotPasswordDataBinding;
    private boolean isOTPSent;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ZoloApplication.getApplicationComponent().inject(this);
        forgotPasswordDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        UserViewModel viewModel = new UserViewModel();
        forgotPasswordDataBinding.setForgotPassword(viewModel);
        forgotPasswordDataBinding.setIsPasswordVisible(false);
        forgotPasswordDataBinding.setIsOTPVisible(false);
        forgotPasswordDataBinding.setIsPhoneNumberVisible(true);
        forgotPasswordDataBinding.setButtonText("SUBMIT");
        forgotPasswordDataBinding.setPasswordInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        forgotPasswordDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_white_24dp);
    }

    /**
     * on click of login button
     *
     * @param view view
     */
    public void onClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * on click of login button
     *
     * @param view view
     */
    public void onClickSubmit(View view) {
        User user = realmDatabaseHelper.getUserWithPhoneNumber(forgotPasswordDataBinding.getForgotPassword().getUser().getPhoneNumber());
        if (!isOTPSent) {
            if (user == null) {
                Utils.showSnackBar("User not exist with this mobile number", view);
            } else {
                isOTPSent = true;
                Utils.showSnackBar("Your OTP is 1234", view);
                forgotPasswordDataBinding.setIsPasswordVisible(true);
                forgotPasswordDataBinding.setIsOTPVisible(true);
                forgotPasswordDataBinding.setIsPhoneNumberVisible(false);
                forgotPasswordDataBinding.setButtonText("RESET PASSWORD");
            }
        } else {
            if (TextUtils.isEmpty(forgotPasswordDataBinding.getOtpText()) || !forgotPasswordDataBinding.getOtpText().equalsIgnoreCase("1234")) {
                Utils.showSnackBar("Incorrect OTP ", view);
            } else if (!forgotPasswordDataBinding.getForgotPassword().isValidPassword()) {
                Utils.showSnackBar("Password should have minimum 8 characters", view);
            } else {
                realmDatabaseHelper.updatePassword(user, forgotPasswordDataBinding.getForgotPassword().getUser().getPassword());
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle("Success")
                        .setMessage("Your password successfully changed")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                                finish();
                            }
                        }).show();
            }
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
            forgotPasswordDataBinding.setPasswordInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            forgotPasswordDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_white_24dp);
        } else {
            isPasswordVisible = true;
            forgotPasswordDataBinding.setPasswordInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            forgotPasswordDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_off_white_24dp);
        }
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
