package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.manager.TariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.manager.UserDao;
import com.mixajlenko.finaltask.ispsystem.dao.manager.UserTariffDao;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTariffServiceTest {
    @Mock
    private UserTariffDao userTariffDaoMock;
    @Mock
    private UserDao userDaoMock;
    @Mock
    private TariffDao tariffDaoMock;
    @Mock
    UserTariff userTariffMock;
    @Mock
    Tariff tariffMock;
    @InjectMocks
    private UserTariffService testingInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void shouldReturnListOfUserTariffs() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getAll()).thenReturn(new ArrayList<>());
        List<UserTariff> tariffs = testingInstance.getAll();
        assertTrue(tariffs.isEmpty());
    }

    @Test
    void shouldReturnUserTariffsById() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getById(1)).thenReturn(new UserTariff());
        UserTariff userTariff = testingInstance.getById(1);
        assertNotNull(userTariff);
    }

    @Test
    void shouldReturnTrueOrFalseAfterAdding() throws SQLException, NamingException {
        userTariffMock = new UserTariff(1, 2, Date.valueOf("2021-06-06"), 0, Date.valueOf("2021-06-07"));
        Mockito.when(userTariffDaoMock.add(userTariffMock)).thenReturn(true);
        boolean result = testingInstance.add(userTariffMock);
        assertTrue(result);
        Mockito.when(userTariffDaoMock.add(new UserTariff())).thenReturn(false);
        result = testingInstance.add(new UserTariff());
        assertFalse(result);
    }

    @Test
    void shouldReturnUserTariffAfterUpdating() throws SQLException, NamingException {
        userTariffMock = new UserTariff(1, 2, Date.valueOf("2021-06-06"), 0, Date.valueOf("2021-06-07"));
        userTariffMock.setStatus(1);
        Mockito.when(userTariffDaoMock.update(userTariffMock)).thenReturn(true);
        boolean result = testingInstance.update(userTariffMock);
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueAfterDeleting() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.delete(1)).thenReturn(true);
        boolean result = testingInstance.delete(1);
        assertTrue(result);
    }

    @Test
    void shouldReturnListOfUserTariffsByUserId() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getAllUserTariffInfoByUserId(1)).thenReturn(new ArrayList<>());
        List<UserTariff> tariffs = testingInstance.getAllUserTariffByUserId(1);
        assertTrue(tariffs.isEmpty());
    }

    @Test
    void shouldReturnListOfTariffsByUserId() throws SQLException, NamingException {
        Mockito.when(testingInstance.getUserTariffList(1)).thenReturn(new ArrayList<>());
        List<Tariff> tariffs = testingInstance.getUserTariffList(1);
        Assertions.assertTrue(tariffs.isEmpty());
    }

    @Test
    void shouldReturnSingleUserTariffsById() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getUserTariffByUserId(1)).thenReturn(new UserTariff());
        userTariffMock = testingInstance.getUserTariffByUserId(1);
        assertNotNull(tariffMock);
    }

    @Test
    void shouldReturnSingleUserTariffsByUserIdTariffId() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getUserTariffByTariffIdUserId(1, 2)).thenReturn(new UserTariff());
        userTariffMock = testingInstance.getUserTariffByTariffIdUserId(1, 2);
        assertNotNull(tariffMock);
    }

    @Test
    void shouldReturnTrueAfterDeletingByUserAndTariffId() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.deleteByUseIdTariffId(1, 2)).thenReturn(true);
        boolean result = testingInstance.deleteByUseIdTariffId(1, 2);
        assertTrue(result);
    }

    @Test
    void testCheckForMonthPayment() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getAllUserTariffInfoByUserId(anyInt())).thenReturn(Collections.singletonList(new UserTariff(0, 0, 0, null, 0, null)));
        Mockito.when(userTariffDaoMock.getById(any())).thenReturn(new UserTariff());
        Mockito.when(userTariffDaoMock.update(any())).thenReturn(true);
        Mockito.when(tariffDaoMock.getById(any())).thenReturn(new Tariff());
        Mockito.when(tariffDaoMock.update(any())).thenReturn(true);
        Mockito.when(userDaoMock.getById(any())).thenReturn(new User());
        Mockito.when(userDaoMock.update(any())).thenReturn(true);

        boolean result = testingInstance.checkForMonthPayment(0);
        assertTrue(result);
    }
}
