package com.example.redis.repository;

import com.example.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate template;

    public Product save(Product product){
        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }

    public List<Product> finaAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id){
        System.out.println("DB call");
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }

//    test
    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "Product Removed";
    }
}
