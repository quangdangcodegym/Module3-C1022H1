package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.Product;
import com.codegym.casetemplate.service.IProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MSProductService extends DBContext implements IProductService {
    private static final String SELLECT_ALL_PRODUCT = "SELECT * FROM c10_qlykhachhang.product;";
    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM c10_qlykhachhang.product where id = ?;";
    private static final String EDIT_PRODUCT = "UPDATE `product` SET `name` = ?, `price` = ?, `description` = ?, `image` = ?, `create_at` = ?, `category_id` = ? WHERE (`id` = ?);";
    private static final String SELLECT_ALL_PRODUCT_BY_ID = "SELECT * FROM c10_qlykhachhang.product where category_id = ?";

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_ALL_CUSTOMER);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELLECT_ALL_PRODUCT);
            while (rs.next()) {
                Product c = getProductFromRs(rs);
                products.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    private Product getProductFromRs(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        java.sql.Date sqlCreatedAt = rs.getDate("create_at");
        java.util.Date uCreatedAt = new java.util.Date(sqlCreatedAt.getTime());

        String image = rs.getString("image");
        int idCategory = rs.getInt("category_id");

        //long id, String name, double price, String description, String image, Date createAt, int idCategory
        Product product = new Product(id, name, price, description, image, uCreatedAt, idCategory);
        return product;

    }

    @Override
    public Product findProductById(Long id) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = getProductFromRs(rs);
                return product;
            }

            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void editProduct(Product Product) {
//        Connection connection = getConnection();
//
//        try {
//            //"UPDATE `customer` SET `name` = ?, `createdat` = ?,
//            // `address` = ?, `image` =?, `idcustomertype` = ? WHERE (`id` = ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PRODUCT);
//            preparedStatement.setString(1, customer.getName());
//            preparedStatement.setDate(2, new java.sql.Date(customer.getCreatedAt().getTime()));
//            preparedStatement.setString(3, customer.getAddress());
//            preparedStatement.setString(4, customer.getImage());
//            preparedStatement.setInt(5, customer.getIdType());
//            preparedStatement.setLong(6, customer.getId());
//
//            preparedStatement.executeUpdate();
//
//            connection.close();
//        } catch (SQLException sqlException) {
//            printSQLException(sqlException);
//        }
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void createProduct(Product Product) {

    }

    @Override
    public List<Product> getAllProductByCategoryId(int idCategory) {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_ALL_PRODUCT_BY_ID);
            preparedStatement.setInt(1, idCategory);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product c = getProductFromRs(rs);
                products.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }
}
