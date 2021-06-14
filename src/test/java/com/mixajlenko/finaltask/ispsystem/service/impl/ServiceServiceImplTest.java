package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.IServiceDao;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceNameException;
import com.mixajlenko.finaltask.ispsystem.model.Service;
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

class ServiceServiceImplTest {

    @Mock
    IServiceDao serviceDaoMock;
    @InjectMocks
    ServiceServiceImpl testingInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() throws SQLException, NamingException {
        when(serviceDaoMock.getAll()).thenReturn(Collections.singletonList(new Service()));
        List<Service> result = testingInstance.getAll();
        assertEquals(Collections.singletonList(new Service.ServiceBuilderImpl().setId(0).setName(null).setDescription(null).build()), result);
    }

    @Test
    void testGetById() throws SQLException, NamingException, NotFoundServiceIdException {
        when(serviceDaoMock.getById(any())).thenReturn(new Service());
        Service result = testingInstance.getById(0);
        assertEquals(new Service.ServiceBuilderImpl().setId(0).setName(null).setDescription(null).build(), result);
    }

    @Test
    void testAdd() throws SQLException, NamingException {
        when(serviceDaoMock.add(any())).thenReturn(true);
        boolean result = testingInstance.add(new Service.ServiceBuilderImpl().setId(0).setName(null).setDescription(null).build());
        assertTrue(result);
    }

    @Test
    void testUpdate() throws SQLException, NamingException {
        when(serviceDaoMock.update(any())).thenReturn(true);
        boolean result = testingInstance.update(new Service.ServiceBuilderImpl().setId(0).setName(null).setDescription(null).build());
        assertTrue(result);
    }

    @Test
    void testDelete() throws SQLException, NamingException {
        when(serviceDaoMock.delete(any())).thenReturn(true);
        boolean result = testingInstance.delete(0);
        assertTrue(result);
    }

    @Test
    void testGetByName() throws SQLException, NamingException, NotFoundServiceNameException {
        when(serviceDaoMock.getByName(anyString())).thenReturn(new Service.ServiceBuilderImpl().setId(0).setName(null).setDescription(null).build());
        Service result = testingInstance.getByName("name");
        assertEquals(new Service.ServiceBuilderImpl().setId(0).setName(null).setDescription(null).build(), result);
    }
}
