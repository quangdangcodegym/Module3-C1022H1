package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.User;
import com.codegym.casetemplate.service.IUserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService extends DBContext implements IUserService {

    private static final String SELLECT_USER_BY_ID = "SELECT * FROM c10_qlykhachhang.user where id  = ?";
    private static final String SELLECT_USER_BY_EMAIL_PASSWORD = "SELECT * FROM c10_qlykhachhang.user where `username` = ? and `password` = ?;";

    private User getUserTypeFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String name = rs.getString("name");
        String password = rs.getString("password");
        int roleId = rs.getInt("id");


        User user = new User(id, name, username, password, roleId);
        return user;

    }

    @Override
    public User findUserById(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_USER_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User c = getUserTypeFromRs(rs);
                return c;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
    @Override
    public User findUserByUserNamePassword(String username, String password) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_USER_BY_EMAIL_PASSWORD);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User c = getUserTypeFromRs(rs);
                return c;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
}
