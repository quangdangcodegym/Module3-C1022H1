package com.codegym.customermanager.service.mysql;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.service.ICustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCustomerService implements ICustomerService {


    private static final String SELLECT_ALL_CUSTOMER = "SELECT * FROM customer";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer` ( `name`, `createdat`, `address`, `image`) VALUES (?, ?,?, ? )";
    private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM `customer` WHERE (`id` = ?);";
    private String jdbcURL = "jdbc:mysql://localhost:3306/c10_qlykhachhang?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "St180729!!";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
    @Override
    public List<Customer> getAllCustomer() {

        List<Customer> customers = new ArrayList<>();
        Connection connection = getConnection();
        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_ALL_CUSTOMER);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELLECT_ALL_CUSTOMER);
            while (rs.next()) {
                Customer c = getCustomerFromRs(rs);
                customers.add(c);
            }


            System.out.println("getAllCustomer: " + statement);
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    private Customer getCustomerFromRs(ResultSet rs) throws SQLException {
        Long id =  rs.getLong("id");
        String name = rs.getString("name");
        java.sql.Date sqlCreatedAt = rs.getDate("createdat");
        java.util.Date uCreatedAt = new java.util.Date(sqlCreatedAt.getTime());
        String address = rs.getString("address");
        String image = rs.getString("image");

        Customer customer = new Customer(id, name, uCreatedAt, address, image);
        return customer;
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
    public Customer findCustomerById(Long id) {
        return null;
    }

    @Override
    public void editCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomerById(Long id) {

        Connection connection = getConnection();
        //DELETE FROM customer` WHERE (`id` = ?)
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_CUSTOMER_BY_ID);
            ps.setLong(1, id);

            ps.executeUpdate();
            // DELETE FROM customer` WHERE (`id` = 3)
            System.out.println("deleteCustomerById: " + ps);
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

    }

    @Override
    public void createCustomer(Customer customer) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);

            //"INSERT INTO customer` ( `name`, `createdat`, `address`, `image`) VALUES (?, ?,?, ? )";
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, (Date) customer.getCreatedAt());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getImage());

            preparedStatement.executeUpdate();
            System.out.println("createCustomer: " + preparedStatement);

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }


    }
}
