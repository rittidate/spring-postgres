package com.arraieot.springpostgres.model.data;

import com.arraieot.springpostgres.model.Cheese;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}
