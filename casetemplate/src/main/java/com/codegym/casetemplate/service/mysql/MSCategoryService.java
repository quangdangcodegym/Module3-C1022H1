package com.codegym.casetemplate.service.mysql;

import com.codegym.casetemplate.model.Category;
import com.codegym.casetemplate.service.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCategoryService extends  DBContext implements ICategoryService {
    private static final String SELLECT_ALL_CATEGORY = "SELECT * FROM c10_qlykhachhang.category;";
    private static final String SELLECT_CATEGORY_BY_ID = "SELECT * FROM c10_qlykhachhang.category where id = ?";

    @Override
    public List<Category> getAllCategorys() {
        List<Category> categories = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_ALL_CATEGORY);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Category c = getCategoryTypeFromRs(rs);
                categories.add(c);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return categories;
    }

    private Category getCategoryTypeFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        Category category = new Category(id, name);
        return category;

    }

    @Override
    public Category getCategoryById(int id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_CATEGORY_BY_ID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Category c = getCategoryTypeFromRs(rs);
                return c;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
}
