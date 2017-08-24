package com.arraieot.springpostgres.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arraieot.springpostgres.model.Category;
import com.arraieot.springpostgres.model.Cheese;
import com.arraieot.springpostgres.model.Menu;
import com.arraieot.springpostgres.model.data.CategoryDao;
import com.arraieot.springpostgres.model.data.CheeseDao;
import com.arraieot.springpostgres.model.data.MenuDao;
import com.arraieot.springpostgres.model.form.AddMenuItemForm;

@Controller
@RequestMapping(value = "menus")
public class MenuController {
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private CheeseDao cheeseDao;
	
	@RequestMapping(value = "")
	public String index(Model model){
		model.addAttribute("title", "My Menu");
		model.addAttribute("menus", menuDao.findAll());
		return "menu/index";
	}
	
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String displayAddForm(Model model){
		model.addAttribute("title", "Add Menu");
		model.addAttribute(new Menu());
		return "menu/add";
	}
	
	@RequestMapping(value = "add", method= RequestMethod.POST)
	public String processAddForm(@ModelAttribute @Valid Menu menu, Errors errors, Model model){

		if(errors.hasErrors()){
			model.addAttribute("title", "Add Cheese");
			return "menu/add";
		}
		
		menuDao.save(menu);
		return "redirect:view/" + menu.getId();
	}
	
	@RequestMapping(value="view/{menuId}", method=RequestMethod.GET)
	public String viewMenu(Model model, @PathVariable int menuId){
		Menu menu = menuDao.findOne(menuId);
		
		model.addAttribute("title", menu.getName());
		model.addAttribute("cheeses", menu.getCheeses());
		model.addAttribute("menuId", menu.getId());
		
		return "menu/view";
	}
	
	@RequestMapping(value="add-item/{menuId}", method= RequestMethod.GET)
	public String addItem(Model model, @PathVariable int menuId){
		Menu menu = menuDao.findOne(menuId);
		
		AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(), menu);
		
		model.addAttribute("title", "Add item to menu: " + menu.getName());
		model.addAttribute("form",  form);
		
		return "menu/add-item";
	}
	
	@RequestMapping(value="add-item", method= RequestMethod.POST)
	public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){
		if(errors.hasErrors()){
			model.addAttribute("form", form);
			return "menu/add-item";
		}
		
		
		Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
		Menu theMenu = menuDao.findOne(form.getMenuId());
		theMenu.addItem(theCheese);
		menuDao.save(theMenu);
		
		return "redirect:/menus/view/" + theMenu.getId();
	}

}
