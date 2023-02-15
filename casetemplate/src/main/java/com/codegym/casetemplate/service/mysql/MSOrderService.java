package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.Order;
import com.codegym.casetemplate.service.IOrderService;
import com.google.gson.Gson;

import java.sql.*;
import java.util.List;

public class MSOrderService extends DBContext implements IOrderService {
    private static final String ADD_ORDER_ITEM = "INSERT INTO `c10_qlykhachhang`.`order_item` (`id_order`, `id_product`, `quantity`) VALUES (?, ?, ?)";
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
                ps.setInt(3, order.getOrderItems().get(i).getQuantiy());
                ps.executeUpdate();

                connection.prepareStatement("INSERT INTO `c10_qlykhachhang`.`order_item` (`id_order`, `id_product`, `quantity`) VALUES ('21', '3', '1')").executeUpdate();

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

    @Override
    public void saveOrderBySP(Order order) {
        /**
//         Thực thi gọi procedure không có tham số
        Connection connection = getConnection();

        Gson gson = new Gson();
        String jsonInString = gson.toJson(order);
        System.out.println(jsonInString);
        try {
            CallableStatement callableStatement = connection.prepareCall("call c10_qlykhachhang.spSaveOrderSP(?)");
            callableStatement.setObject(1, jsonInString);

            callableStatement.executeUpdate();

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);

        }
         **/

        // Gọi procedure có tham số
        saveOrderBySPWithParameter(order);
    }

    public void saveOrderBySPWithParameter(Order order) {
        Connection connection = getConnection();

        Gson gson = new Gson();
        String jsonInString = gson.toJson(order);
        System.out.println(jsonInString);
        try {
            CallableStatement callableStatement = connection.prepareCall("call c10_qlykhachhang.spSaveOrderWithParameter(?, ?)");
            callableStatement.setObject(1, jsonInString);
            callableStatement.registerOutParameter(2, Types.BOOLEAN);


            callableStatement.executeUpdate();
            boolean sOutput = callableStatement.getBoolean(2);

            System.out.println("Kết quả thực thi với Procedure: " + sOutput);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);

        }
    }




}
