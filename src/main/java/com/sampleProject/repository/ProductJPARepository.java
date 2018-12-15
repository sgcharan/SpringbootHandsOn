package com.sampleProject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sampleProject.entity.Products;
@Repository
public interface ProductJPARepository extends JpaRepository<Products, Long> {
		public Products findByName(String name);
		
		public List<Products> findByAdded(String added);
		
		
		public int countByUsername(String added);
		
		public List<Products> findByUsername(String username);
		public List<Products> findByForusername(String username);
		
		@Query("SELECT DISTINCT p.forusername FROM Products p WHERE p.username IN (:username)")
		List<String> findDistinctLinkUser(@Param("username") String username);
		
		@Query("SELECT p.forusername,u.othersaddress,u.otherscontact,u.othersemail,p.name,p.weight,p.price FROM Products p, Userlist u WHERE p.forusername = u.others and p.username IN (:username)")
		List<String> findProductsForInvoice(@Param("username") String username);		

}
