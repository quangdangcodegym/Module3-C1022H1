package com.codegym.casetemplate.service;


import com.codegym.casetemplate.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategorys();

    Category getCategoryById(int id);
}
