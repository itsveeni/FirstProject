package com.prodcat.Controller;

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

import com.prodcat.Entity.Category;
import com.prodcat.Entity.ObjectNotFoundException;
import com.prodcat.Service.CategoryService;

@RestController
@RequestMapping("CategoryApi")
public class CategoryController {

	@Autowired
	CategoryService cs;

	@PostMapping("savecategory") //request-body annotation calls setter methods to update objects with values given in json string
	public Category saveCategory(@RequestBody Category cat)
	{
		System.out.println("Data coming from browser "+cat);
		Category s= cs.saveCategory(cat);
		return s; // return s because we want our object to represent into j-son string in response postman
	}

	@GetMapping("category/{cid}")
	public Category getCategory(@PathVariable int cid)
	{
		Category cat = cs.getCategory(cid);
		if(cat==null)
		{
			throw new ObjectNotFoundException("No record found at cid "+cid);
		}
		else
		{
			return cat;
		}
	}

	@PutMapping("updatecategory")
	public Category updateCategory(@RequestBody Category cat)
	{
		return cs.updateCategory(cat);
	}

	@DeleteMapping("deletecategory/{cid}")
	public Category deletecategory(@PathVariable int cid)
	{
		System.out.println("category from client "+cid);
		Category cat = cs.deletecategory(cid);

		if(cat==null)
		{
			throw new ObjectNotFoundException("No record found at cid "+cid+" to delete");
		}
		else
		{
			return cat;
		}
	}

	@GetMapping("getallcategory")
	public List<Category> getallcategory()
	{
		return cs.getallcategory();
	}
}
