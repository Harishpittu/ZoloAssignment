package com.technologies.pittu.zoloassignment.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;
import com.technologies.pittu.zoloassignment.databinding.ForgotPasswordDataBinding;
import com.technologies.pittu.zoloassignment.model.User;
import com.technologies.pittu.zoloassignment.utils.Utils;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import javax.inject.Inject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    @Inject
    RealmDatabaseHelper realmDatabaseHelper;

    ForgotPasswordDataBinding forgotPasswordDataBinding;

    private boolean isOTPSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ZoloApplication.getApplicationComponent().inject(this);
        forgotPasswordDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        UserViewModel viewModel = new UserViewModel();
        forgotPasswordDataBinding.setForgotPassword(viewModel);
        forgotPasswordDataBinding.setIsPasswordVisible(false);
        forgotPasswordDataBinding.setButtonText("SUBMIT");
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
                forgotPasswordDataBinding.setButtonText("RESET PASSWORD");
            }
        } else {
            if (!forgotPasswordDataBinding.getForgotPassword().isValidPassword()) {
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
                            }
                        }).show();
            }
        }
    }

}
