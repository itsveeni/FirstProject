package com.prodcat.Controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.prodcat.Entity.*;

@Controller
public class WebController {

	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("/")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("products")
	public String products()
	{
		return "products";
	}
	
	
	@RequestMapping("validate")
	public ModelAndView validate(User userFromBrowser)
	{

		// userFromBrowser==> [username="java" password="java"] User class object
		
		ModelAndView modelAndView = new ModelAndView();
		
		String username=userFromBrowser.getUsername();
		String password=userFromBrowser.getPassword();
		
		if(username.equals("admin") && password.equals("admin123"))
		{
			modelAndView.setViewName("apicall");
		}
		
		else if(username.equals("merchant") && password.equals("merchant123"))
		{
			modelAndView.setViewName("productmanagement");
		}
		
		else
		{
					Session session = factory.openSession();
					
					User userfromDatabase=session.get(User.class,username);
			        
					// userFromDatabase==> [username="java" password="java123"] User class object
				    
					// get() gives null if record is NOT found in database
					
				if(userfromDatabase!=null)
				{
					if(password.equals(userfromDatabase.getPassword()))
					{
						modelAndView.addObject("username",username);
						modelAndView.setViewName("products");
					}
					else
					{
						modelAndView.addObject("message","wrong password");
						modelAndView.setViewName("login");
					}
				}
				
				else
				{
					modelAndView.addObject("message","wrong credentials");
					modelAndView.setViewName("login");
				
				}
				
		}
		
			return modelAndView;
	
	}
	
	@RequestMapping("showregister")
	String showregister()
	{
		return "register";
	}
	

	@RequestMapping("savedata")
	ModelAndView savedata(User user,HttpServletRequest request)
	{

		
		MultipartFile filedata=user.getImages();
		String filename=filedata.getOriginalFilename();
		
		System.out.println(filename);// xyz.jpg

		String[] strings=filename.split("\\.");
		
		String fileextension=strings[1];
		
		File file=new File(request.getServletContext().getRealPath("/images"), user.getUsername()+"."+fileextension);
				
		try {
			
			filedata.transferTo(file);
			
			System.out.println("File uploaded successfully");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		
	    Session session = factory.openSession();
		
	    Transaction tx=session.beginTransaction();
	    
	    	session.save(user);
	    	
	    tx.commit();
	    
	    modelAndView.addObject("message","Registration successful");
	    
	    modelAndView.setViewName("login");
	    
	    return modelAndView;
	    
	}
	
	@RequestMapping("saveProduct")
	public ModelAndView addProduct(Products product,HttpServletRequest request,int cid)
	{

		MultipartFile filedata=product.getImages();
		String filename=filedata.getOriginalFilename();
		
		System.out.println(filename);// one.jpg
		String[] strings=filename.split("\\.");
		
		String fileextension=strings[1];


		File file=new File(request.getServletContext().getRealPath("/images"), product.getPid()+"."+fileextension);
				
		try {
			
			filedata.transferTo(file);
			
			System.out.println("File uploaded successfully");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		ModelAndView modelAndView = new ModelAndView(); 
		
        Session session=factory.openSession();
		
		Category category=session.load(Category.class,cid);
		
		System.out.println("Products from given catergory are :- " + category.getProducts());
		
		/* get list of product and add product into it  */
		
		List<Products> productlist=category.getProducts();
					
		Transaction transaction=session.beginTransaction();
		
			productlist.add(product);
						
		transaction.commit();
				
		System.out.println("product added into database");
	
	    
		modelAndView.addObject("message","Product information is saved successfully");
	    
	    modelAndView.setViewName("productmanagement");
	    
	    return modelAndView;
	    
	}


	
	
}
