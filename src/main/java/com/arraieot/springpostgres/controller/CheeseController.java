package com.arraieot.springpostgres.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arraieot.springpostgres.model.Category;
import com.arraieot.springpostgres.model.Cheese;
import com.arraieot.springpostgres.model.CheeseData;
import com.arraieot.springpostgres.model.CheeseType;
import com.arraieot.springpostgres.model.data.CategoryDao;
import com.arraieot.springpostgres.model.data.CheeseDao;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {
	
	@Autowired
	private CheeseDao cheeseDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	//Request path: /cheese
	@RequestMapping(value = "")
	public String index(Model model){		
		model.addAttribute("cheeses", cheeseDao.findAll());
		model.addAttribute("title", "My Cheese");
		return "cheese/index";
	}
	
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String displayAddCheeseForm(Model model){
		model.addAttribute("title", "Add Cheese");
		model.addAttribute("categories", categoryDao.findAll());
		model.addAttribute(new Cheese());
		return "cheese/add";
	}
	
	@RequestMapping(value = "remove", method= RequestMethod.GET)
	public String displayRemoveCheeseForm(Model model){
		model.addAttribute("title", "Remove Cheese");
		model.addAttribute("cheeses", cheeseDao.findAll());
		return "cheese/remove";
	}
	
	@RequestMapping(value = "remove", method= RequestMethod.POST)
	public String processRemoveCheeseForm(@RequestParam int[] cheeseIds){
		
		for(int cheeseId : cheeseIds){
			cheeseDao.delete(cheeseId);
		}
		return "redirect:/cheese";
	}
	
	@RequestMapping(value = "add", method= RequestMethod.POST)
	public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int categoryId, Model model){
		
		if(errors.hasErrors()){
			model.addAttribute("title", "Add Cheese");
			model.addAttribute("categories", categoryDao.findAll());
			return "cheese/add";
		}
		
		Category cat = categoryDao.findOne(categoryId);
		newCheese.setCategory(cat);
		cheeseDao.save(newCheese);
		return "redirect:/cheese";
		
	}
	
	@RequestMapping(value="category", method= RequestMethod.GET)
	public String category(Model model, @RequestParam int id){
		Category cat = categoryDao.findOne(id);
		List<Cheese> cheeses = cat.getCheeses();
		model.addAttribute("cheeses", cheeses);
		model.addAttribute("title", "Cheese in Category: " + cat.getName());
		return "cheese/index";
	}
}
