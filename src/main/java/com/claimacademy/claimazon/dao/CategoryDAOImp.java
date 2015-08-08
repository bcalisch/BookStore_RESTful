package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by benjamin on 7/30/15.
 */
public class CategoryDAOImp implements CategoryDAO {
    private SQLConnection connection = new SQLConnection();

    @Override
    public ArrayList<Category> categoryCount() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String sql = "Select BC.Name, Count(BC.Book_ID) as \"Count\"\n" +
                "From BookStore.BookCategory BC\n" +
                " \n" +
                "Group by BC.Name";
        ResultSet rs = connection.selectBuilder(sql);
        try {
            categories = new Transformer().transformResultSetToCategoryCount(rs);
            rs.close();
            connection.conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return categories;
    }

    @Override
    public ArrayList<Category> findAllCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            String sqlCategory="Select * from BookStore.Category";
            ResultSet rsCategory = connection.selectBuilder(sqlCategory);
            categories = new Transformer().transformResultSetToCategoryCount(rsCategory);
            rsCategory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }


}
