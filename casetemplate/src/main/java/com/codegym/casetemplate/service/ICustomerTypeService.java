package com.codegym.casetemplate.service;

import com.codegym.casetemplate.model.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    List<CustomerType> getAllCustomerTypes();

    CustomerType getCustomerTypeById(int id);

}
