package com.technologies.pittu.zoloassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;
import com.technologies.pittu.zoloassignment.data.SharedPrefsHelper;
import com.technologies.pittu.zoloassignment.model.User;

import javax.inject.Inject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    @Inject
    RealmDatabaseHelper realmDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ZoloApplication.zoloApplication().getApplicationComponent().inject(this);
//        User user = new User();
//        user.setPhoneNumber("123");
//        user.setName("harish79879879");
//        user.setEmail("harish@gmai");
//        user.setPassword("1235");
//        realmDatabaseHelper.copyORUpdate(user);
//        Toast.makeText(this, ""+realmDatabaseHelper.getUserWithPhoneNumber("123").getName(), Toast.LENGTH_SHORT).show();
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
     * @param view view
     */
    public void onClickLogin(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    /**
     * on click of create account
     * @param view view
     */
    public void onClickCreateAccount(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * on click of forgot password
     * @param view view
     */
    public void onClickForgotPassword(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
