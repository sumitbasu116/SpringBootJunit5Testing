package com.sumit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.entity.Product;
import com.sumit.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product/v1")
@Slf4j
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		log.info("ProductController called for addProduct request");
		Product responseProduct = productService.addProduct(product);
		return new ResponseEntity<>(responseProduct,HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Product> updateStock(@PathVariable("name") String productName){
		Product fetchedProduct=productService.getProductByName(productName);
		return new ResponseEntity<>(fetchedProduct,HttpStatus.OK);
	}
}
