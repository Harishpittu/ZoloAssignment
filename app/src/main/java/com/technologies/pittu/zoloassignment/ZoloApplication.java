package com.technologies.pittu.zoloassignment;

import android.app.Application;

import com.technologies.pittu.zoloassignment.dependencyInjection.component.ApplicationComponent;
import com.technologies.pittu.zoloassignment.dependencyInjection.module.AppModule;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class ZoloApplication extends Application {
    private static ZoloApplication zoloApplication;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        zoloApplication = this;

//        applicationComponent = DaggerApplicationComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
//        applicationComponent.inject(this);
    }

    public static ZoloApplication zoloApplication() {
        return zoloApplication;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
