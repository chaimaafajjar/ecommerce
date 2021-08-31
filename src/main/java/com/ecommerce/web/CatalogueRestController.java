package com.ecommerce.web;

import com.ecommerce.dao.CategoryRepository;
import com.ecommerce.dao.ProductRepository;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public CatalogueRestController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Product p = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ecom/products/" + p.getPhotoName()));


    }

    @GetMapping(path = "/photoCategory/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhotoCat(@PathVariable("id") Long id) throws Exception {
        Category c = categoryRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ecom/categories/" + c.getPhotoCat()));
    }
}
