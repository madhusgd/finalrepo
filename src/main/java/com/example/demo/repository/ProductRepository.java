package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
 
	@Override
    Product findOne(String id);

    @Override
    void delete(Product deleted);
}