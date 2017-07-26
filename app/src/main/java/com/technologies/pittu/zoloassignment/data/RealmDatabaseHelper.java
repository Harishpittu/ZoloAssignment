package com.technologies.pittu.zoloassignment.data;


import com.technologies.pittu.zoloassignment.model.User;
import com.technologies.pittu.zoloassignment.model.Words;

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
     * @param user user object
     * @return bool
     */
    public boolean doesUserExists(User user) {
        User storedUser = realm.where(User.class).equalTo("phoneNumber", user.getPhoneNumber()).equalTo("password", user.getPassword()).findFirst();
        return storedUser != null;
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
     * get all words
     *
     */
    public Words getWords() {
        return realm.where(Words.class).findFirst();
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
     * get update password
     *
     * @param password string
     * @return user
     */
    public void updatePassword(final User user, final String password) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                user.setPassword(password);
            }
        });
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
