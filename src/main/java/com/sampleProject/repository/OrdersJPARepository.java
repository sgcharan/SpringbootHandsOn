package com.sampleProject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sampleProject.entity.Orders;
import com.sampleProject.entity.Userlist;
@Repository
public interface OrdersJPARepository extends JpaRepository<Orders, Long> {
		

}
