package com.technologies.pittu.zoloassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;

import javax.inject.Inject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class RegisterActivity extends AppCompatActivity {

    @Inject
    RealmDatabaseHelper realmDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ZoloApplication.getApplicationComponent().inject(this);

        Toast.makeText(this, ""+realmDatabaseHelper.getUserWithPhoneNumber("123").getName(), Toast.LENGTH_SHORT).show();
//
    }
    /**
     * on click of login button
     * @param view view
     */
    public void onClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * on click of register
     * @param view view
     */
    public void onClickRegister(View view) {

    }
}
