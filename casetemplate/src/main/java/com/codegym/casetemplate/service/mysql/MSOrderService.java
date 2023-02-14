package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.Order;
import com.codegym.casetemplate.service.IOrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSOrderService extends DBContext implements IOrderService {
    private static final String ADD_ORDER_ITEM = "INSERT INTO `c10_qlykhachhang`.`order_item` (`id_order`, `id_product`) VALUES (?, ?)";
    String ADD_ORDER = "INSERT INTO `c10_qlykhachhang`.`order` (`address`, `phone`, `name`, `total`) VALUES (?, ?, ?, ?)";
    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public Order getOrderById(long id) {
        return null;
    }

    @Override
    public void saveOrder(Order order) {
        Connection connection = getConnection();
//        String ADD_ORDER = "INSERT INTO `c10_qlykhachhang`.`order` (`address`, `phone`, `name`, `total`)
//        VALUES ('28 NTP', '3456789', 'Quang', '12');\n";
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setString(1, order.getAddress());
            preparedStatement.setString(2, order.getPhone());
            preparedStatement.setString(3, order.getName());
            preparedStatement.setDouble(4, order.getTotal());
            preparedStatement.executeUpdate();


            ResultSet rs = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            while (rs.next()) {
                order.setId(rs.getLong(1));
            }
            for (int i = 0; i < order.getOrderItems().size(); i++) {
                //INSERT INTO `c10_qlykhachhang`.`order_item` (`id_order`, `id_product`) VALUES (?, ?)";
                PreparedStatement ps = connection.prepareStatement(ADD_ORDER_ITEM);
                ps.setLong(1, order.getId());
                ps.setLong(2, order.getOrderItems().get(i).getIdProduct());
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
