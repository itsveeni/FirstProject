package com.prodcat.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodcat.Dao.ProductsDao;
import com.prodcat.Entity.ProductInfowithcategories;
import com.prodcat.Entity.Products;

@Service
public class ProductsService {

	@Autowired
	ProductsDao pd;
	
	public Products addproducts(Products products, int cid)
	{
		return pd.addproducts(products, cid);
	}
	
	public Products updateproducts(Products products)
	{
		return pd.updateproducts(products);
	}
	
	public Products getproducts(int pid)
	{
		return pd.getproducts(pid);
	}
	
	public List<Products> allproducts()
	{
		return pd.allproducts();
	}
	
	public ArrayList<ProductInfowithcategories> allproductswithcategory()
	{
		return pd.allproductswithcategory();
	}
	
	public ArrayList<ProductInfowithcategories> getproductswithcategory(int pid)
	{
		return pd.getproductswithcategory(pid);
	}
	
	public Products deleteproducts(int pid)
	{
		return pd.deleteproducts(pid);
	}
}
