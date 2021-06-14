package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceNameException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
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

class TariffServiceImplTest {

    @Mock
    ITariffDao tariffDaoMock;
    @InjectMocks
    TariffServiceImpl testingInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() throws SQLException, NamingException {
        when(tariffDaoMock.getAll()).thenReturn(Collections.singletonList(new Tariff()));
        List<Tariff> result = testingInstance.getAll();
        assertEquals(Collections.singletonList(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build()), result);
    }

    @Test
    void testGetById() throws SQLException, NamingException, NotFoundServiceIdException {
        when(tariffDaoMock.getById(any())).thenReturn(new Tariff());
        Tariff result = testingInstance.getById(0);
        assertEquals(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build(), result);
    }

    @Test
    void testAdd() throws SQLException, NamingException {
        when(tariffDaoMock.add(any())).thenReturn(true);
        boolean result = testingInstance.add(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build());
        assertTrue(result);
    }

    @Test
    void testUpdate() throws SQLException, NamingException {
        when(tariffDaoMock.update(any())).thenReturn(true);
        boolean result = testingInstance.update(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build());
        assertTrue(result);
    }

    @Test
    void testDelete() throws SQLException, NamingException {
        when(tariffDaoMock.delete(any())).thenReturn(true);
        boolean result = testingInstance.delete(0);
        assertTrue(result);
    }

    @Test
    void testSetServiceTariff() throws SQLException, NamingException {
        when(tariffDaoMock.setServiceTariff(anyInt(), anyInt())).thenReturn(true);
        boolean result = testingInstance.setServiceTariff(0, 0);
        assertTrue(result);
    }

    @Test
    void testGetServiceTariff() throws SQLException, NamingException {
        when(tariffDaoMock.getServiceTariff(anyInt())).thenReturn(Collections.singletonList(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build()));
        List<Tariff> result = testingInstance.getServiceTariff(0);
        assertEquals(Collections.singletonList(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build()), result);
    }

    @Test
    void testGetByName() throws SQLException, NamingException, NotFoundServiceNameException {
        when(tariffDaoMock.getByName(anyString())).thenReturn(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build());
        Tariff result = testingInstance.getByName("name");
        assertEquals(new Tariff.TariffBuilderImpl().setId(0).setName(null).setDescription(null).setPrice(0).build(), result);
    }
}