package com.arraieot.springpostgres.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min=3, max=50)
	private String name;
	
	@OneToMany
	@JoinColumn(name = "category_id")
	private List<Cheese> cheeses = new ArrayList<>();
	
	public Category(){}
	
	public Category(String name){
		this.name = name;
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

	public List<Cheese> getCheeses() {
		return cheeses;
	}

	public void setCheeses(List<Cheese> cheeses) {
		this.cheeses = cheeses;
	}
}
