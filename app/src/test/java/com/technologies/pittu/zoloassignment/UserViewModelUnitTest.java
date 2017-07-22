package com.technologies.pittu.zoloassignment;

import com.technologies.pittu.zoloassignment.data.ProvideDataForTest;
import com.technologies.pittu.zoloassignment.model.User;
import com.technologies.pittu.zoloassignment.viewmodel.UserViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class UserViewModelUnitTest {

    private User user;
    private UserViewModel userViewModel;

    @Before
    public void setUpUserViewModel()
    {
        user = ProvideDataForTest.getUser();
        userViewModel = new UserViewModel(user);
    }

    @Test public void shouldGetUserName() throws Exception {
        assertEquals(user.getName(), userViewModel.getUserName());
    }
    @Test public void passwordShouldValid() throws Exception {
        assertEquals(true, userViewModel.isValidPassword());
    }
    @Test public void shouldGetUserPhoneNumber() throws Exception {
        assertEquals(user.getPhoneNumber(), userViewModel.getUserPhoneNumber());
    }
    @Test public void shouldGetUserEmail() throws Exception {
        assertEquals(user.getEmail(), userViewModel.getUserEmail());
    }
    @Test public void shouldGetUserPassword() throws Exception {
        assertEquals(user.getPassword(), userViewModel.getUserPassword());
    }
}