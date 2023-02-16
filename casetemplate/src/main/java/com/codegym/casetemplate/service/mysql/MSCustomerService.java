package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.Customer;
import com.codegym.casetemplate.service.ICustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCustomerService extends DBContext implements ICustomerService {


    private static final String SELLECT_ALL_CUSTOMER = "SELECT * FROM customer";
    private static final String INSERT_CUSTOMER = "INSERT INTO `customer` ( `name`, `createdat`, `address`, `image`, `idcustomertype`) VALUES (?, ?,?, ?, ? )";
    private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM `customer` WHERE (`id` = ?);";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customer where id = ?;";
    private static final String EDIT_CUSTOMER = "UPDATE `customer` SET `name` = ?, `createdat` = ?, `address` = ?, `image` =?, `idcustomertype` = ? WHERE (`id` = ?)";
    private static final String CHECK_IMAGE_EXIST = "SELECT * FROM c10_qlykhachhang.customer where image = ?;";

//
    private static final String SEARCHING_PAGGING_CUSTOMER = "SELECT SQL_CALC_FOUND_ROWS  * FROM customer where `name` like ? and idcustomertype = ?  limit ?, ?";
    private static final String SEARCHING_PAGGING_CUSTOMER_ALL = "SELECT SQL_CALC_FOUND_ROWS  * FROM customer where `name` like ? limit ?, ?;";

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
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
        Date sqlCreatedAt = rs.getDate("createdat");
        java.util.Date uCreatedAt = new java.util.Date(sqlCreatedAt.getTime());
        String address = rs.getString("address");
        String image = rs.getString("image");
        int idCustomerType = rs.getInt("idcustomertype");

        Customer customer = new Customer(id, name, uCreatedAt, address, image);
        customer.setIdType(idCustomerType);
        return customer;
    }


    @Override
    public Customer findCustomerById(Long id) {

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = getCustomerFromRs(rs);
                return customer;
            }

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void editCustomer(Customer customer) {
        Connection connection = getConnection();

        try {
            //"UPDATE `customer` SET `name` = ?, `createdat` = ?,
            // `address` = ?, `image` =?, `idcustomertype` = ? WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, new Date(customer.getCreatedAt().getTime()));
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getImage());
            preparedStatement.setInt(5, customer.getIdType());
            preparedStatement.setLong(6, customer.getId());

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
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

//            "INSERT INTO `customer` ( `name`, `createdat`, `address`, `image`, `idcustomertype`) VALUES (?, ?,?, ?, ? )";
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, new Date(customer.getCreatedAt().getTime()));
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getImage());
            preparedStatement.setInt(5, customer.getIdType());

            preparedStatement.executeUpdate();
            System.out.println("createCustomer: " + preparedStatement);

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }


    }

    @Override
    public boolean checkImageExists(String image) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IMAGE_EXIST);
            preparedStatement.setString(1, image);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // khỏi cần đọc dòng này ra vì chỉ cần trả ra true/false
                return true;
            }

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomerSearchingPagging(String kw, int idCustomerType, int offset, int limit) {
        List<Customer> customers = new ArrayList<>();
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = null;
            if (idCustomerType == -1) {
                preparedStatement = connection.prepareStatement(SEARCHING_PAGGING_CUSTOMER_ALL);
                preparedStatement.setString(1, "%" + kw + "%");
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, limit);
            }else{
                preparedStatement = connection.prepareStatement(SEARCHING_PAGGING_CUSTOMER);
                preparedStatement.setString(1, "%" + kw + "%");
                preparedStatement.setInt(2, idCustomerType);
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, limit);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer c = getCustomerFromRs(rs);
                customers.add(c);
            }

            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            while (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }
}
