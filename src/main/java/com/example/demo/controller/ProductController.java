package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public Iterable<Product> product() {
		return productRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public String save(@RequestBody Product product) {
		productRepository.save(product);

		return product.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public Product show(@PathVariable String id) {
		return productRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
	public Product update(@PathVariable String id, @RequestBody Product product) {
		Product prod = productRepository.findOne(id);
		if (product.getProdName() != null)
			prod.setProdName(product.getProdName());
		if (product.getProdDesc() != null)
			prod.setProdDesc(product.getProdDesc());
		if (product.getProdPrice() != null)
			prod.setProdPrice(product.getProdPrice());
		productRepository.save(prod);
		return prod;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
	public String delete(@PathVariable String id) {
		Product product = productRepository.findOne(id);
		productRepository.delete(product);

		return "product deleted";
	}
}