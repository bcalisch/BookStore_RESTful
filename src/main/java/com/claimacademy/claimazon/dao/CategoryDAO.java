package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Category;

import java.util.ArrayList;


public interface CategoryDAO {
     ArrayList<Category> findAllCategories();
     ArrayList<Category> categoryCount();

}
