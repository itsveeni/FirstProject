package com.prodcat.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class User {

	@Id
	String username;
	String password;
	String email;
	
   transient MultipartFile images;
	
	
	public MultipartFile getImages() {
		return images;
	}
	public void setImages(MultipartFile images) {
		this.images = images;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
}

