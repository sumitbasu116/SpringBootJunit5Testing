package com.sumit.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sumit.entity.Product;
import com.sumit.exception.ProductNotFoundException;
import com.sumit.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	// Constructor Injection (preferred approach in modern Spring applications)
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product addProduct(Product product) {

		Product savedProduct = productRepository.save(product);
		log.info("Product saved successfully in DB");
		return savedProduct;
	}

	public Product getProductByName(String productName) {
		Optional<Product> productByName = productRepository.findByName(productName);
		if (productByName.isPresent()) {
			return productByName.get();
		} else {
			log.error("Product with Name: {} not found", productName);
			throw new ProductNotFoundException(productName);
		}
	}
	
	//private method to demonstrate private method testing
	private boolean validateProductName(String name) {
		return name!=null && !name.isEmpty();
	}
	
	// Void method to demonstrate mocking of voids
	public void deleteProductById(long productId) {
		productRepository.deleteById(productId);
	}
	// Void method to demonstrate mocking of voids
	public void updateProductStock(long productId, int quantity) {
		Optional<Product> productById = productRepository.findById(productId);
		if (productById.isPresent()) {
			Product product = productById.get();
			int newStockCount = product.getStock() + quantity;
			product.setStock(newStockCount);
			Product saveProduct = productRepository.save(product);
			log.info("New stock level for product {}: {}", productId, saveProduct.getStock());
		} else {
			log.warn("Product with ID: {} not found", productId);
		}
	}
}
