package com.ecommerce;

import com.ecommerce.dao.CategoryRepository;
import com.ecommerce.dao.ProductRepository;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {


	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

      repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);

		categoryRepository.save(new Category(null,"Ordinateur",null,"computers.png",null));
		categoryRepository.save(new Category(null,"Phone",null,"smartphones.png",null));
		categoryRepository.save(new Category(null,"Printers",null,"printers.png",null));
		categoryRepository.save(new Category(null,"TV",null,"computers.png",null));
		categoryRepository.save(new Category(null,"Clothes",null,"printers.png",null));
		categoryRepository.save(new Category(null,"Camera",null,"smartphones.png",null));
		Random rnd=new Random();
		categoryRepository.findAll().forEach(c->{
			for (int i = 0; i < 10; i++) {
				Product p = new Product();
				p.setName(RandomString.make(18));
				p.setCurrentprice(100 + rnd.nextInt(10000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("computers.png");
				productRepository.save(p);
			}
		});
	}
}
