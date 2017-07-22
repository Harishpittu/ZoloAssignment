package com.technologies.pittu.zoloassignment.data;

import com.technologies.pittu.zoloassignment.model.User;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class ProvideDataForTest {
    private static final String USER_NAME = "harish";
    private static final String USER_PHONE_NUMBER = "12341234";
    private static final String USER_EMAIL = "test@test.com";
    private static final String USER_PASSWORD = "test@test.com";

    /**
     * create dummy object
     * @return user
     */
    public static User getUser() {
        User user = new User();
        user.setPassword(USER_PASSWORD);
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPhoneNumber(USER_PHONE_NUMBER);
        return user;
    }
}
