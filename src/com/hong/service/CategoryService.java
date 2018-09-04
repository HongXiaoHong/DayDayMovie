package com.hong.service;

import java.util.List;

import com.hong.bean.Category;
import com.hong.dao.CategoryDao;

public class CategoryService {

	private CategoryDao dao = new CategoryDao();
	public List<Category> getAllCategory(){
		return dao.selectAll();
	}
}
