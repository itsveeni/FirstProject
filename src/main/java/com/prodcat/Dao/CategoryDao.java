package com.prodcat.Dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prodcat.Entity.Category;

@Repository
public class CategoryDao {

	@Autowired
	SessionFactory sf;

	public Category saveCategory(Category cat)
	{
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();
		ss.save(cat);
		System.out.println(cat);
		tr.commit();
		return cat;

	}

	public Category getCategory(int cid)
	{
		Category cat = sf.openSession().get(Category.class, cid);

		//Session ss = sf.openSession();
		//Criteria ctr = ss.createCriteria(Category.class);
		//ctr.add(Restrictions.eq("cid", cid));
		//List<Category> c = ctr.list();
		//Category cat = c.get(0); //0th element, because we are going to get only one element by the provided id
		//System.out.println(cat);
		return cat;

	}

	public Category updateCategory(Category cat)
	{
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction(); //criteria api is only for retrieving data
		ss.update(cat);
		tr.commit();
		return cat;

	}

	public Category deletecategory(int cid) 
	{
		Session ss = sf.openSession();	
		Transaction tr = ss.beginTransaction();
		Category cat = ss.get(Category.class, cid);
		
		ss.delete(cat);
		tr.commit();

		return cat;	
	}
	
	public List<Category> getallcategory()
	{
		Session ss = sf.openSession();
		Criteria ctr = ss.createCriteria(Category.class);
	    
		List<Category> l=ctr.list();
		for (Category category : l) {
			category.setProducts(null);
		}
		return l;
	}
}
