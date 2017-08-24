package com.arraieot.springpostgres.model.data;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arraieot.springpostgres.model.Category;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {

}
