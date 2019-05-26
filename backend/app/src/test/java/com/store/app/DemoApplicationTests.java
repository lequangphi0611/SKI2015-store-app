package com.store.app;

import static org.junit.Assert.assertTrue;

import com.store.service.CategoryService;
import com.store.service.ProductService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.store")
public class DemoApplicationTests {

	@Autowired
	CategoryService cateService;

	@Autowired
	ProductService productService;

	@Test
	public void contextLoads() {
		assertTrue(cateService.findAll().size() == 1);
	}

	@Test
	public void testExistsByNameCategory() {
		assertTrue(cateService.existsByName("Iphone"));
	}

	@Test
	public void testProductService() {
		assertTrue(productService.findAll().size() == 0);
	}

}
