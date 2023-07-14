package com.codegym.customermanager.service.inmemory;

import com.codegym.customermanager.model.CustomerType;
import com.codegym.customermanager.service.ICustomerTypeService;

import java.util.ArrayList;
import java.util.List;

public class CustomerTypeService implements ICustomerTypeService {
    private List<CustomerType> customerTypes;

    @Override
    public List<CustomerType> getAllCustomerTypes() {
        return this.customerTypes;
    }

    @Override
    public CustomerType getCustomerTypeById(int id) {
        for (int i = 0; i < customerTypes.size(); i++) {
            if (customerTypes.get(i).getId() == id) {
                return customerTypes.get(i);
            }
        }
        return null;
    }

    public CustomerTypeService() {
        customerTypes = new ArrayList<>();
        customerTypes.add(new CustomerType(1, "VIP"));
        customerTypes.add(new CustomerType(2, "SUPER VIP"));
        customerTypes.add(new CustomerType(3, "NORMAL"));
    }

}
