package com.sumit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sumit.entity.Product;
import com.sumit.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	@Test
	public void addProductToDBSuccessfully(){
		log.info("Add product testing...");
		
		//1. Data Preparation
		Product product = new Product(1, "product-abc", 300, 2);
		
		/*
		 * The test data should not go into the actual DB. To mock the 
		* repository call, we can use Mockito when function.
		* Mockito when is defined or read as when addProduct method of 
		* ProductService called with a product then return then the same product
		* */
		//2. Mocking call logic if any
		Mockito.when(productService.addProduct(product)).thenReturn(product);
		
		//3. Actual test method call
		Product addedProduct = productService.addProduct(product);
		
		//4.  Assertions logic
		Assertions.assertEquals(product.getId(), addedProduct.getId());
		Assertions.assertTrue(product.getId()==1);
	}
	
	@Test
	public void deleteProductToDeleteFromDB() {
		log.info("Delete product testing...");
		//1.Data
		long deleteId=1;
		
		//2. Mocking call logic if any
		Mockito.doNothing().when(productRepository).deleteById(deleteId);
		
		//3. Actual test method call
		productService.deleteProductById(deleteId);
		
		//4.  Assertions logic
		//verify the deleteById method of productRepository class is called one time.
		Mockito.verify(productRepository, times(1)).deleteById(deleteId);
	}
	
	@Test
	//private method can be tested by using Java reflection feature.
	public void validateProductValidNamePrivateTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method validateProductNameMethod = 
				productService.getClass().getDeclaredMethod("validateProductName",String.class);
		validateProductNameMethod.setAccessible(true);
		boolean result =(boolean) validateProductNameMethod.invoke(productService,"product-abc");
		Assertions.assertTrue(result);
	}
	@Test
	//private method can be tested by using Java reflection feature.
	public void validateProductInValidNamePrivateTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method validateProductNameMethod = 
				productService.getClass().getDeclaredMethod("validateProductName",String.class);
		validateProductNameMethod.setAccessible(true);
		boolean result =(boolean) validateProductNameMethod.invoke(productService,"");
		Assertions.assertFalse(result);
	}
	
	@Test
	public void addProductShouldThrowExceptionForInvalidProductName(){
		log.info("Add product testing...");
		
		Product product = new Product(1, "", 300, 2);
		
		RuntimeException runtimeException = assertThrows(RuntimeException.class, ()->{
			productService.addProduct(product);
		});
		//check the specific message
		assertEquals("Invalid Product Name", runtimeException.getMessage());
	}
}
