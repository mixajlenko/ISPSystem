package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.IPaymentsDao;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    IPaymentsDao paymentsDaoMock;
    @InjectMocks
    PaymentServiceImpl testingInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() throws SQLException, NamingException {
        when(paymentsDaoMock.getAll()).thenReturn(Collections.singletonList(new Payment()));
        List<Payment> result = testingInstance.getAll();
        assertEquals(Collections.singletonList(new Payment.PaymentsBuilderImpl().setId(0).setBill(0).setStatus(0).setBalance(0).setDate(null).setType(null).build()), result);

    }

    @Test
    void testGetById() throws SQLException, NamingException, NotFoundServiceIdException {
        when(paymentsDaoMock.getById(any())).thenReturn(new Payment());
        Payment result = testingInstance.getById(0);
        assertEquals(new Payment.PaymentsBuilderImpl().setId(0).setBill(0).setStatus(0).setBalance(0).setDate(null).setType(null).build(), result);
    }

    @Test
    void testAdd() throws SQLException, NamingException {
        when(paymentsDaoMock.add(any())).thenReturn(true);
        boolean result = testingInstance.add(new Payment.PaymentsBuilderImpl().setId(0).setBill(0).setStatus(0).setBalance(0).setDate(null).setType(null).build());
        assertTrue(result);
    }

    @Test
    void testUpdate() throws SQLException, NamingException {
        when(paymentsDaoMock.update(any())).thenReturn(true);
        boolean result = testingInstance.update(new Payment.PaymentsBuilderImpl().setId(0).setBill(0).setStatus(0).setBalance(0).setDate(null).setType(null).build());
        assertTrue(result);
    }

    @Test
    void testDelete() throws SQLException, NamingException {
        when(paymentsDaoMock.delete(any())).thenReturn(true);
        boolean result = testingInstance.delete(0);
        assertTrue(result);
    }

    @Test
    void testGetAllById() throws SQLException, NamingException {
        when(paymentsDaoMock.getAllById(anyInt())).thenReturn(Collections.singletonList(new Payment.PaymentsBuilderImpl().setId(0).setBill(0).setStatus(0).setBalance(0).setDate(null).setType(null).build()));
        List<Payment> result = testingInstance.getAllById(0);
        assertEquals(Collections.singletonList(new Payment.PaymentsBuilderImpl().setId(0).setBill(0).setStatus(0).setBalance(0).setDate(null).setType(null).build()), result);
    }

    @Test
    void testExceptionInGetAllMethod() throws SQLException, NamingException {
        when(paymentsDaoMock.getAll()).thenThrow(ServiceException.class);
        assertThrows(ServiceException.class, () -> testingInstance.getAll());
    }
}