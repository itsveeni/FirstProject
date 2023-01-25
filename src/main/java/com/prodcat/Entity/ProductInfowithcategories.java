package com.prodcat.Entity;

public class ProductInfowithcategories {

	int pid;
	String name;
	int price;
	int cid;
	String cname;
	
	public ProductInfowithcategories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductInfowithcategories(int pid, String name, int price, int cid, String cname) {
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.cname = cname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	 
	
}
