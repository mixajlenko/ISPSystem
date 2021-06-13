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
import java.util.ArrayList;
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
        when(userDaoMock.getAll()).thenReturn(getUsers());
        List<User> result = testingInstance.getAll();
        assertEquals(3,result.size());
    }

    @Test
    void testGetById() throws SQLException, NamingException {
        when(userDaoMock.getById(any())).thenReturn(new User());
        User result = testingInstance.getById(0);
        assertEquals(new User.UserBuilderImpl()
                .setId(0)
                .setFirstName(null)
                .setSecondName(null)
                .setPhone(null)
                .setEmail(null)
                .setStatus(0)
                .setWallet(0)
                .setPassword(null)
                .setRole(0)
                .build(), result);
    }

    @Test
    void testAdd() throws SQLException, NamingException {
        when(userDaoMock.add(any())).thenReturn(true);
        boolean result = testingInstance.add(new User.UserBuilderImpl()
                .setId(0)
                .setFirstName(null)
                .setSecondName(null)
                .setPhone(null)
                .setEmail(null)
                .setStatus(0)
                .setWallet(0)
                .setPassword(null)
                .setRole(0)
                .build());
        assertTrue(result);
    }

    @Test
    void testUpdate() throws SQLException, NamingException {
        when(userDaoMock.update(any())).thenReturn(true);
        boolean result = testingInstance.update(new User.UserBuilderImpl()
                .setId(0)
                .setFirstName(null)
                .setSecondName(null)
                .setPhone(null)
                .setEmail(null)
                .setStatus(0)
                .setWallet(0)
                .setPassword(null)
                .setRole(0)
                .build());
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
        when(userDaoMock.getUserByEmail(anyString())).thenReturn(new User.UserBuilderImpl()
                .setId(0)
                .setFirstName(null)
                .setSecondName(null)
                .setPhone(null)
                .setEmail(null)
                .setStatus(0)
                .setWallet(0)
                .setPassword(null)
                .setRole(0)
                .build());
        User result = testingInstance.getUserByEmail("email");
        assertEquals(new User.UserBuilderImpl()
                .setId(0)
                .setFirstName(null)
                .setSecondName(null)
                .setPhone(null)
                .setEmail(null)
                .setStatus(0)
                .setWallet(0)
                .setPassword(null)
                .setRole(0)
                .build(), result);
    }

    @Test
    void testGetByLoginAndPass() throws SQLException, NamingException {
        when(userDaoMock.getUserByEmail("email1")).thenReturn(getUsers().get(0));
        User result = testingInstance.getByLoginAndPass("email1", "password");
        assertEquals(getUsers().get(0), result);
    }

    @Test
    void testBlockedAccounts() {
        int result = testingInstance.blockedAccounts(getUsers());
        assertEquals(1, result);
    }

    private List<User> getUsers() {
        List<User> list = new ArrayList<>();
        User user = new User.UserBuilderImpl()
                .setFirstName("fName1")
                .setSecondName("sName")
                .setPhone("phone")
                .setEmail("email1")
                .setStatus(1)
                .setWallet(0)
                .setPassword("password")
                .setRole(1)
                .build();
        User user1 = new User.UserBuilderImpl()
                .setFirstName("fName2")
                .setSecondName("sName")
                .setPhone("phone")
                .setEmail("email1")
                .setStatus(0)
                .setWallet(0)
                .setPassword("password")
                .setRole(1)
                .build();
        User user2 = new User.UserBuilderImpl()
                .setFirstName("fName3")
                .setSecondName("sName")
                .setPhone("phone")
                .setEmail("email1")
                .setStatus(1)
                .setWallet(0)
                .setPassword("password")
                .setRole(1)
                .build();
        list.add(user);
        list.add(user1);
        list.add(user2);
        return list;
    }
}