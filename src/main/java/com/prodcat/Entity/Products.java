package com.prodcat.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Products {

	@Id
	private int pid;
	private String name;
	private int price;

	transient MultipartFile images;
	
	public MultipartFile getImages() {
		return images;
	}

	public void setImages(MultipartFile images) {
		this.images = images;
	}

	
	
	
	public Products() {
		super();
	}

	public Products(int pid, String name, int price) {
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
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

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", name=" + name + ", price=" + price + "]";
	}


}
