package com.codegym.casetemplate.service;

import com.codegym.casetemplate.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {
    List<Order> getAllOrder();
    Order getOrderById(long id);

    void saveOrder(Order order);


    void saveOrderBySP(Order order);
}
