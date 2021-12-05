package com.example.redis;

import com.example.redis.entity.Product;
import com.example.redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class RedisApplication {

	@Autowired
	private ProductDao dao;

	@PostMapping
	public Product save(@RequestBody Product product){
		return dao.save(product);
	}

	@GetMapping
//	@Cacheable(key = "#id" ,value = "Product")
	public List<Product> getAllProduct(){
		return dao.finaAll();
	}

	@GetMapping("/{id}")
	@Cacheable(key = "#id" ,value = "Product")
	public Product getProduct(@PathVariable int id){
		return dao.findProductById(id);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id", value = "Product")
	public String remove(@PathVariable int id){
		return dao.deleteProduct(id);
	}
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
