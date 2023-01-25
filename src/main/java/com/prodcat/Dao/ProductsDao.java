package com.prodcat.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prodcat.Entity.Category;
import com.prodcat.Entity.ProductInfowithcategories;
import com.prodcat.Entity.Products;

@Repository
public class ProductsDao {

	@Autowired
	SessionFactory sf;
	
	public Products addproducts(Products products, int cid)
	{
		Session ss = sf.openSession();
		Category cat = ss.load(Category.class, cid);
		List<Products> prolist = cat.getProducts();
		Transaction tr = ss.beginTransaction(); 
		prolist.add(products); //we are adding the new products from client to our original list of products from category
		tr.commit();
		return products;
		
	}
	
	public Products updateproducts(Products products)
	{
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();
		ss.update(products);
		tr.commit();
		return products;
		
	}
	
	public Products getproducts(int pid)
	{
        Session session=sf.openSession();
		
		Products product=session.get(Products.class,pid);
		
		return product;
			
	}

	
	public List<Products> allproducts()
	{
		Session ss = sf.openSession();
		Criteria ctr = ss.createCriteria(Products.class);
		List<Products> list = ctr.list();
		return list;
	}
	
	public ArrayList<ProductInfowithcategories> allproductswithcategory()
	{
		Session ss = sf.openSession();
		Query query = ss.createSQLQuery(" select products.pid,products.name,products.price,category.cid,category.name as cname from products join category on products.cid=category.cid;");
		List<Object[]> products = query.list(); //here object array is created because only 5 variables are there and not  the whole table so object array
		ArrayList<ProductInfowithcategories> list = new ArrayList<>();
		for (Object[] array : products) 
		{
			list.add(new ProductInfowithcategories((int)array[0], (String)array[1], (int)array[2], (int)array[3], (String)array[4]));
		}
		return list;
		//but this method and query is giving only array and not json strings
		/*
		 * this object array don't have getter methods to create the json strings that's why it gives array as output
		 * so to create json strings we made new class with those variables and their getter methods using which we can create now json strings
		 */
	}
	

	public ArrayList<ProductInfowithcategories> getproductswithcategory(int pid)
	{
		Session ss = sf.openSession();
		Query query = ss.createSQLQuery(" select products.pid,products.name,products.price,category.cid,category.name as cname from products join category on products.cid=category.cid and products.pid="+pid);
		List<Object[]> products = query.list(); //here object array is created because only 5 variables are there and not  the whole table so object array
		ArrayList<ProductInfowithcategories> list = new ArrayList<>();
		for (Object[] array : products) 
		{
			list.add(new ProductInfowithcategories((int)array[0], (String)array[1], (int)array[2], (int)array[3], (String)array[4]));
		}
		return list;
		//in this method only mentioned pid product with its category name will print
	}
	
	public Products deleteproducts(int pid)
	{
		Session ss = sf.openSession();
		Products prod = ss.get(Products.class, pid);
		Transaction tr = ss.beginTransaction();
	    ss.delete(prod);
		tr.commit();
		return prod;
	}
	
}
