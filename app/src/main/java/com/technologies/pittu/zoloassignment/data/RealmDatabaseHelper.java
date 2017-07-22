package com.technologies.pittu.zoloassignment.data;


import com.technologies.pittu.zoloassignment.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Copyright (c) on 21/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

@Singleton
public class RealmDatabaseHelper {

    // realm object for data fetching and saving
    private Realm realm;

    @Inject
    public RealmDatabaseHelper(Realm realm) {
        this.realm = realm;
    }

    /**
     * to check whether user exists or not
     *
     * @param phoneNumber string
     * @return bool
     */
    public boolean doesUserExists(String phoneNumber) {
        User user = realm.where(User.class).equalTo("phoneNumber", phoneNumber).findFirst();
        return user != null;
    }

    /**
     * get user with phonenumber
     *
     * @param phoneNumber string
     * @return user
     */
    public User getUserWithPhoneNumber(String phoneNumber) {
        return realm.where(User.class).equalTo("phoneNumber", phoneNumber).findFirst();
    }

    /**
     * get user with email
     *
     * @param email string
     * @return user
     */
    public User getUserWithEmail(String email) {
        return realm.where(User.class).equalTo("email", email).findFirst();
    }

    /**
     * clear all data in realm
     */
    public void clearRealm() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public void copyORUpdate(final List<? extends RealmObject> objects) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(objects);
            }
        });
    }

    public void copyORUpdate(final RealmObject realmObject) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmObject);
            }
        });
    }

    public void copyToRealm(final RealmObject realmObject) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmObject);
            }
        });
    }

    public void copyToRealm(final List<? extends RealmObject> objects) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(objects);
            }
        });
    }


}