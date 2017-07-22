package com.technologies.pittu.zoloassignment.dependencyInjection.component;


import android.app.Application;
import android.content.Context;

import com.technologies.pittu.zoloassignment.dependencyInjection.module.AppModule;
import com.technologies.pittu.zoloassignment.view.ForgotPasswordActivity;
import com.technologies.pittu.zoloassignment.view.LoginActivity;
import com.technologies.pittu.zoloassignment.view.RegisterActivity;
import com.technologies.pittu.zoloassignment.view.UserProfileActivity;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Copyright (c) on 21/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    void inject(Application application);
    void inject(Context context);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(UserProfileActivity userProfileActivity);
    void inject(ForgotPasswordActivity forgotPasswordActivity);
    void inject(UserViewModel userViewModel);
}