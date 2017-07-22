package com.technologies.pittu.zoloassignment.dependencyInjection.component;


import android.app.Application;

import com.technologies.pittu.zoloassignment.dependencyInjection.module.AppModule;
import com.technologies.pittu.zoloassignment.view.LoginActivity;

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
    void inject(LoginActivity loginActivity);
}