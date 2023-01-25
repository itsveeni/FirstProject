package com.prodcat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodcat.Dao.CategoryDao;
import com.prodcat.Entity.Category;

@Service
public class CategoryService {

	@Autowired	
	CategoryDao cd;
	
	public Category saveCategory(Category cat)
	{
		Category s=cd.saveCategory(cat);
		return s;
	}
	
	public Category getCategory(int cid)
	{
		Category cat = cd.getCategory(cid);
		return cat;
	}

	public Category updateCategory(Category cat) 
	{
	
		return cd.updateCategory(cat);
	}

	public Category deletecategory(int cid) 
	{
		
		return cd.deletecategory(cid);
	}
	
	public List<Category> getallcategory() 
	{
		return cd.getallcategory();
	}
	
}
