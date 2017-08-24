package com.arraieot.springpostgres.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cheese {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min=3, max=15)
	private String name;
	@NotNull
	@Size(min=1, message="Description must not be empty!!")
	private String description;
	
	//private CheeseType type;
	
	@ManyToOne
	private Category category;
	
	@ManyToMany(mappedBy = "cheeses")
	private List<Menu> menus;
	
	
	public Cheese(){
	}
	
	public Cheese(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	/*
	public CheeseType getType() {
		return type;
	}

	public void setType(CheeseType type) {
		this.type = type;
	}
	*/
	
	
	
}
