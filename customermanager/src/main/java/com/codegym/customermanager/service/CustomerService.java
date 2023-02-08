package com.codegym.customermanager.service;


import com.codegym.customermanager.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService implements ICustomerService{

    private List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();

        //Customer(Long id, String name, Date createdAt, String address, String image) {

        customers.add(new Customer(1L, "Quang Dang", new Date(), "28 NTP", null, 3));
        customers.add(new Customer(2L, "Huu Nghia", new Date(), "28 NTP", null, 1));
        customers.add(new Customer(3L, "Thanh Nhan", new Date(), "28 NTP", null, 1));
        customers.add(new Customer(4L, "Bich Thuy", new Date(), "28 NTP", null, 2));
    }

    @Override
    public List<Customer> getAllCustomer() {
        return this.customers;
    }

    @Override
    public Customer findCustomerById(Long id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return customers.get(i);
            }
        }
        return null;
    }

    @Override
    public void editCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.get(i).setAddress(customer.getAddress());
                customers.get(i).setName(customer.getName());
                customers.get(i).setImage(customer.getImage());
                customers.get(i).setCreatedAt(customer.getCreatedAt());
            }
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                customers.remove(i);
            }
        }
    }

    @Override
    public void createCustomer(Customer customer) {
        customer.setId((long) (customers.size()+1));
        customers.add(customer);
    }
}
