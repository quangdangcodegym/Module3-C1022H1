package com.codegym.customermanager.service.mysql;

import com.codegym.customermanager.model.CustomerType;
import com.codegym.customermanager.service.ICustomerTypeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCustomerTypeService implements ICustomerTypeService {
    private static final String SELLECT_ALL_CUSTOMER_TYPE = "SELECT * FROM customer_type;";
    private static final String SELECT_CUSTOMER_TYPE_BY_ID = "SELECT * FROM customer_type where id = ?";
    private String jdbcURL = "jdbc:mysql://localhost:3306/c10_qlykhachhang?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "St180729!!";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

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
