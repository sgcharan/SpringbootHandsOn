package com.sampleProject.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sampleProject.entity.Products;
import com.sampleProject.repository.ProductJPARepository;

@SpringBootApplication
@ComponentScan({"com.sampleProject"})
@EntityScan({"com.sampleProject.entity"})
@EnableJpaRepositories({"com.sampleProject.repository"})
public class SpringBootWebApp implements CommandLineRunner {
	
	@Autowired
	ProductJPARepository productJpaRepo;
/*	
	@Autowired
	Products products;*/
	
	@Autowired
	Environment env;
	
	public static void main(String[] args)   {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootWebApp.class, args); 
	}


	
	@Override
	public void run(String... args) throws Exception {
/*		// TODO Auto-generated method stub
		System.out.println("set values in entity");
		products.setName("Milk");
		products.setPrice(55);
		products.setWeight("500ml");*/

		
		System.out.println("saving  values to DB");
		productJpaRepo.save(new Products("Milk",35,"500ml",null,env.getProperty("milk_img_url"),"All" ));
		productJpaRepo.save(new Products("Butter",100,"250g",null,env.getProperty("butter_img_url"),"All" ));
		productJpaRepo.save(new Products("Ghee",250,"200ml",null,env.getProperty("ghee_img_url"),"All" ));
		productJpaRepo.save(new Products("Honey",500,"500ml",null,env.getProperty("honey_img_url"),"All" ));
		
		System.out.println("retrieving price by name "+productJpaRepo.findByName("Milk").getPrice());
		System.out.println("retrieving all products "+productJpaRepo.findAll());
		
	}

	
	
}
