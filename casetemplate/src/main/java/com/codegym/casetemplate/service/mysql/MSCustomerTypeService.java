package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.CustomerType;
import com.codegym.casetemplate.service.ICustomerTypeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCustomerTypeService extends DBContext implements ICustomerTypeService {
    private static final String SELLECT_ALL_CUSTOMER_TYPE = "SELECT * FROM customer_type;";
    private static final String SELECT_CUSTOMER_TYPE_BY_ID = "SELECT * FROM customer_type where id = ?";





    @Override
    public List<CustomerType> getAllCustomerTypes() {
        List<CustomerType> customerTypes = new ArrayList<>();
        Connection connection = getConnection();
        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_ALL_CUSTOMER);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELLECT_ALL_CUSTOMER_TYPE);
            while (rs.next()) {
                CustomerType c = getCustomerTypeFromRs(rs);
                customerTypes.add(c);
            }

            System.out.println("getAllCustomerTypes: " + statement.toString());
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerTypes;
    }

    private CustomerType getCustomerTypeFromRs(ResultSet rs) throws SQLException {
        CustomerType customerType = new CustomerType();
        int id = rs.getInt("id");
        String name = rs.getString("name");

        customerType.setId(id);
        customerType.setName(name);
        return customerType;
    }

    @Override
    public CustomerType getCustomerTypeById(int id) {
        Connection connection = getConnection();
        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_ALL_CUSTOMER);

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_TYPE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CustomerType c = getCustomerTypeFromRs(rs);
                return c;
            }

            System.out.println("getCustomerTypeById: " + preparedStatement);
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
}
