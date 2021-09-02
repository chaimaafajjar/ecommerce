package com.ecommerce.web;

import com.ecommerce.dao.CategoryRepository;
import com.ecommerce.dao.ProductRepository;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping(path="/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        Category category=categoryRepository.findById(id).get();
        for(Product p:category.getProducts())
        {
            productRepository.deleteById(p.getId());
        }

          categoryRepository.deleteById(id);
    }

    @DeleteMapping(path="/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);

    }

    @PostMapping(path="/category")
    public void createCategory(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @PostMapping(path="/product")
    public void createProduct(@RequestBody Product product){
        productRepository.save(product);
    }

    @PutMapping(path="/category/{id}")
    public void updateCategory(@RequestBody Category c , @PathVariable("id") Long id ){
       Category category =categoryRepository.findById(id).get();
        category.setName(c.getName());
       category.setDescription(c.getDescription());
       category.setPhotoCat(c.getPhotoCat());
       categoryRepository.save(category);

    }

    @PutMapping(path="/product/{id}")
    public void updateProduct(@RequestBody Product p , @PathVariable("id") Long id ){
        Product product =productRepository.findById(id).get();
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setCurrentprice(p.getCurrentprice());
        product.setPromotion(p.isPromotion());
        product.setSelected(p.isSelected());
        product.setAvailable(p.isAvailable());
        product.setPhotoName(p.getPhotoName());
        product.setCategory(p.getCategory());
        productRepository.save(product);

    }







}
