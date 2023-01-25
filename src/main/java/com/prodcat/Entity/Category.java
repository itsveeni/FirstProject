package com.prodcat.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Category {

 @Id
 int cid;
 String name;
	
		
	@OneToMany(targetEntity=Products.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cid")
	private List<Products> products;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Category(int cid, String name, List<Products> products) {
		super();
		this.cid = cid;
		this.name = name;
		this.products = products;
	}



	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", name=" + name + ", products=" + products + "]";
	}
	
	
	
}
