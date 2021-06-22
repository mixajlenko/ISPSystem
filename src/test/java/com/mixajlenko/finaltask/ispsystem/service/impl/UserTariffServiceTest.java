package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.manager.TariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.manager.UserDao;
import com.mixajlenko.finaltask.ispsystem.dao.manager.UserTariffDao;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        userTariffMock = new UserTariff.UserTariffBuilderImpl().setUserId(1).setTariffId(2).setSubDate(Date.valueOf("2021-06-06")).setStatus(0).setNextBill(Date.valueOf("2021-06-07")).build();
        Mockito.when(userTariffDaoMock.add(userTariffMock)).thenReturn(true);
        boolean result = testingInstance.add(userTariffMock);
        assertTrue(result);
        Mockito.when(userTariffDaoMock.add(new UserTariff())).thenReturn(false);
        result = testingInstance.add(new UserTariff());
        assertFalse(result);
    }

    @Test
    void shouldReturnUserTariffAfterUpdating() throws SQLException, NamingException {
        userTariffMock = new UserTariff.UserTariffBuilderImpl().setUserId(1).setTariffId(2).setSubDate(Date.valueOf("2021-06-06")).setStatus(0).setNextBill(Date.valueOf("2021-06-07")).build();
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
    void shouldReturnListOfTariffsByUserId() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getUserTariffByUserId(1)).thenReturn(getUserTariffs());
        for(UserTariff tariff : getUserTariffs()){
            Mockito.when(tariffDaoMock.getById(tariff.getTariffId())).thenReturn(new Tariff());
        }
        List<Tariff> tariffs = testingInstance.getUserTariffList(1);
        assertEquals(1,tariffs.size());
    }


    @Test
    void shouldReturnSingleUserTariffsByUserIdTariffId() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getUserTariffByTariffIdUserId(1, 2)).thenReturn(new UserTariff());
        userTariffMock = testingInstance.getUserTariffByTariffIdUserId(1, 2);
        assertNotNull(tariffMock);
    }

    @Test
    void shouldReturnTrueAfterDeletingByUserAndTariffId() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.deleteByUserIdTariffId(1, 2)).thenReturn(true);
        boolean result = testingInstance.deleteByUseIdTariffId(1, 2);
        assertTrue(result);
    }

    @Test
    void testCheckForMonthPayment() throws SQLException, NamingException {
        Mockito.when(userTariffDaoMock.getUserTariffByUserId(1)).thenReturn(getUserTariffs());
        Mockito.when(userTariffDaoMock.getById(1)).thenReturn(new UserTariff.UserTariffBuilderImpl().setUserId(1).setTariffId(1).setSubDate(Date.valueOf("2021-04-04")).setStatus(1).setNextBill(Date.valueOf("2021-05-05")).build());
        Mockito.when(userTariffDaoMock.update(new UserTariff.UserTariffBuilderImpl().setUserId(1).setTariffId(1).setSubDate(Date.valueOf("2021-04-04")).setStatus(0).setNextBill(Date.valueOf("2021-05-05")).build())).thenReturn(true);
        Mockito.when(userDaoMock.getById(1)).thenReturn(new User.UserBuilderImpl()
                .setFirstName("fName1")
                .setSecondName("sName")
                .setPhone("phone")
                .setEmail("email1")
                .setStatus(1)
                .setWallet(0)
                .setPassword("password")
                .setRole(1)
                .build());
        Mockito.when(userDaoMock.update(new User.UserBuilderImpl()
                .setFirstName("fName1")
                .setSecondName("sName")
                .setPhone("phone")
                .setEmail("email1")
                .setStatus(0)
                .setWallet(0)
                .setPassword("password")
                .setRole(1)
                .build())).thenReturn(true);

        boolean result = testingInstance.checkForMonthPayment(1);
        assertTrue(result);
    }

    private List<UserTariff> getUserTariffs() {
        List<UserTariff> list = new ArrayList<>();
        UserTariff userTariff1 = new UserTariff.UserTariffBuilderImpl().setUserId(1).setTariffId(1).setSubDate(Date.valueOf("2021-04-04")).setStatus(1).setNextBill(Date.valueOf("2021-05-05")).build();
//        UserTariff userTariff2 = new UserTariff(1,2,Date.valueOf("2021-06-04"),1,Date.valueOf("2021-05-05"));
        list.add(userTariff1);
//        list.add(userTariff2);
        return list;
    }
    private List<Tariff> getTariffs() {
        List<Tariff> list = new ArrayList<>();
        Tariff tariff1 = new Tariff.TariffBuilderImpl().setId(1).setName("name1").setDescription("description1").setPrice(1).build();
        Tariff tariff2 = new Tariff.TariffBuilderImpl().setId(2).setName("name2").setDescription("description2").setPrice(2).build();
        list.add(tariff1);
        list.add(tariff2);
        return list;
    }

}
