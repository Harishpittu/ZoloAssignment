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
import com.technologies.pittu.zoloassignment.databinding.RegisterDataBinding;
import com.technologies.pittu.zoloassignment.utils.Utils;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import javax.inject.Inject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class RegisterActivity extends AppCompatActivity {

    @Inject
    RealmDatabaseHelper realmDatabaseHelper;
    RegisterDataBinding registerDataBinding;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ZoloApplication.getApplicationComponent().inject(this);
        registerDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        UserViewModel viewModel = new UserViewModel();
        registerDataBinding.setRegister(viewModel);
        registerDataBinding.setPasswordInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        registerDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_white_24dp);
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
     * on click of register
     *
     * @param view view
     */
    public void onClickRegister(View view) {
        if (realmDatabaseHelper.getUserWithPhoneNumber(registerDataBinding.getRegister().getUser().getPhoneNumber())!=null) {
            Utils.showSnackBar("Mobile number already exists", view);
        } else if (!registerDataBinding.getRegister().isValidPassword()) {
            Utils.showSnackBar("Password should have minimum 8 characters", view);
        } else if (!registerDataBinding.getRegister().isAllFieldsValid()) {
            Utils.showSnackBar("Please enter all fields", view);
        } else {
            realmDatabaseHelper.copyORUpdate(registerDataBinding.getRegister().getUser());
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Success")
                    .setMessage("Your Account has been successfully created , please login to see your profile")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getBaseContext(), LoginActivity.class));
                            finish();
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
            registerDataBinding.setPasswordInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            registerDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_white_24dp);
        } else {
            isPasswordVisible = true;
            registerDataBinding.setPasswordInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            registerDataBinding.setPasswordVisibleIcon(R.drawable.ic_visibility_off_white_24dp);
        }
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
