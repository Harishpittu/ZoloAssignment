package com.technologies.pittu.zoloassignment.dependencyInjection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    public Realm provideRealm(Context context) {
        Realm.init(context);
        return Realm.getDefaultInstance();
    }

    @Singleton
    @Provides
    public RealmDatabaseHelper provideRealmDatabaseHelper(Realm realm) {
        return new RealmDatabaseHelper(realm);
    }
}
