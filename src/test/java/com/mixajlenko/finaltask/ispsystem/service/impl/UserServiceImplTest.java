package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    IUserDao userDaoMock;
    @InjectMocks
    UserServiceImpl testingInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() throws SQLException, NamingException {
        when(userDaoMock.getAll()).thenReturn(Collections.singletonList(new User()));
        List<User> result = testingInstance.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetById() throws SQLException, NamingException {
        when(userDaoMock.getById(any())).thenReturn(new User());
        User result = testingInstance.getById(0);
        assertEquals(new User(0, null, null, null, null, 0, 0, null, 0), result);
    }

    @Test
    void testAdd() throws SQLException, NamingException {
        when(userDaoMock.add(any())).thenReturn(true);
        boolean result = testingInstance.add(new User(0, null, null, null, null, 0, 0, null, 0));
        assertTrue(result);
    }

    @Test
    void testUpdate() throws SQLException, NamingException {
        when(userDaoMock.update(any())).thenReturn(true);
        boolean result = testingInstance.update(new User(0, null, null, null, null, 0, 0, null, 0));
        assertTrue(result);
    }

    @Test
    void testDelete() throws SQLException, NamingException {
        when(userDaoMock.delete(any())).thenReturn(true);
        boolean result = testingInstance.delete(0);
        assertTrue(result);
    }

    @Test
    void testGetUserByEmail() throws SQLException, NamingException {
        when(userDaoMock.getUserByEmail(anyString())).thenReturn(new User(0, null, null, null, null, 0, 0, null, 0));
        User result = testingInstance.getUserByEmail("email");
        assertEquals(new User(0, null, null, null, null, 0, 0, null, 0), result);
    }

    @Test
    void testGetByLoginAndPass() throws SQLException, NamingException {
        when(userDaoMock.getUserByEmail(anyString())).thenReturn(new User(0, null, null, null, null, 0, 0, "password", 0));
        User result = testingInstance.getByLoginAndPass("login", "password");
        assertEquals(new User(0, null, null, null, null, 0, 0, "password", 0), result);
    }

    @Test
    void testBlockedAccounts() {
        int result = testingInstance.blockedAccounts(Collections.singletonList(new User()));
        assertEquals(1, result);
    }
}