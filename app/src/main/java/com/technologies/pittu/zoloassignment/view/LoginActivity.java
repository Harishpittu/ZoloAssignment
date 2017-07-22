package com.technologies.pittu.zoloassignment.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;
import com.technologies.pittu.zoloassignment.data.SharedPrefsHelper;
import com.technologies.pittu.zoloassignment.databinding.LoginDataBinding;
import com.technologies.pittu.zoloassignment.utils.Utils;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import javax.inject.Inject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    LoginDataBinding loginDataBinding;

    @Inject
    RealmDatabaseHelper realmDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ZoloApplication.getApplicationComponent().inject(this);
        if (sharedPrefsHelper.isUserLoggedIn()) {
            startActivity(new Intent(this, UserProfileActivity.class));
            finish();
        }
        loginDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        UserViewModel viewModel = new UserViewModel();
        loginDataBinding.setLogin(viewModel);
//      View mContentView ;
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    /**
     * on click of login button
     *
     * @param view view
     */
    public void onClickLogin(View view) {
        if (realmDatabaseHelper.doesUserExists(loginDataBinding.getLogin().getUser())) {
            sharedPrefsHelper.savePhoneNumber(loginDataBinding.getLogin().getUser().getPhoneNumber());
            startActivity(new Intent(this, UserProfileActivity.class));
            finish();
        } else {
            Utils.showSnackBar("Invalid Username/Password", view);
        }
    }

    /**
     * on click of create account
     *
     * @param view view
     */
    public void onClickCreateAccount(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    /**
     * on click of forgot password
     *
     * @param view view
     */
    public void onClickForgotPassword(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
