package com.prodcat.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodcat.Entity.ObjectNotFoundException;
import com.prodcat.Entity.ProductInfowithcategories;
import com.prodcat.Entity.Products;
import com.prodcat.Service.ProductsService;

@RestController
@RequestMapping("ProductsApi")
public class ProductController {

	@Autowired
	ProductsService ps;
	
	
	@PostMapping("products/{cid}")
	public Products addproducts(@RequestBody Products products,@PathVariable int cid)
	{
		return ps.addproducts(products, cid);
		
	}
	
	@PutMapping("updateproducts")
	public Products updateproducts(@RequestBody Products products)
	{
		return ps.updateproducts(products);
	}
	
	@GetMapping("getproducts/{pid}")
	public Products getproducts(@PathVariable int pid)
	{
		Products product = ps.getproducts(pid);	
		if(product==null)
		{
			throw new ObjectNotFoundException("No record found at product id "+pid);
		}
		else
		{
		return product;
		}
	}
	
	@GetMapping("allproducts")
	public List<Products> allproducts()
	{
		return ps.allproducts();
	}
	
	@GetMapping("allproductswithcategory")
	public ArrayList<ProductInfowithcategories> allproductswithcategory()
	{
		return ps.allproductswithcategory();
	}
	
	@GetMapping("getproductswithcategory/{pid}")
	public ArrayList<ProductInfowithcategories> getproductswithcategory(@PathVariable int pid)
	{
		return ps.getproductswithcategory(pid);
	}
	
	@DeleteMapping("deleteproducts/{pid}")
	public Products deleteproducts(@PathVariable int pid)
	{
		return ps.deleteproducts(pid);
	}
}
