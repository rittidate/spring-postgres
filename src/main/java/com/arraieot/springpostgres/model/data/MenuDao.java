package com.arraieot.springpostgres.model.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arraieot.springpostgres.model.Cheese;
import com.arraieot.springpostgres.model.Menu;

@Repository
@Transactional
public interface MenuDao extends CrudRepository<Menu, Integer> {

}
