package com.sumit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumit.entity.Product;
import com.sumit.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ProductController.class)
@Slf4j
public class ProductControllerTest {

	private static final String ADD_PRODUCT_URL="/product/v1/add";
	private static final String GET_PRODUCT_URL="/product/v1/product-abc";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ProductService productService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void addProductTest() throws Exception {
		Product input =new Product(1, "product-abc", 300, 2);
		
		String requestBody=objectMapper.writeValueAsString(input);
		
		Product savedProductOutput = new Product(1, "product-abc", 300, 2);
		
		Mockito.when(productService.addProduct(Mockito.any(Product.class)))
		.thenReturn(savedProductOutput);
		
		mockMvc.perform(post(ADD_PRODUCT_URL)
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(savedProductOutput.getId()))
                .andExpect(jsonPath("$.name").value(savedProductOutput.getName()))
                .andExpect(jsonPath("$.price").isNumber());
				
		log.info(objectMapper.writeValueAsString(savedProductOutput));			
	}
	
	@Test
	public void getProductTest() throws Exception {
		Product savedProductOutput = new Product(1, "product-abc", 300, 2);
		String outputString=objectMapper.writeValueAsString(savedProductOutput);
		
		Mockito.when(productService.getProductByName(savedProductOutput.getName()))
		.thenReturn(savedProductOutput);
		
		mockMvc.perform(get(GET_PRODUCT_URL))
		.andExpect(status().isOk())
        .andExpect(content().string(outputString));//content() method should be from MockMvcResultMatchers
				
		log.info(objectMapper.writeValueAsString(savedProductOutput));			
	}
	
}
