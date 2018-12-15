package com.sampleProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampleProject.entity.Orders;
import com.sampleProject.entity.Userlist;
import com.sampleProject.repository.OrdersJPARepository;
import com.sampleProject.repository.UserlistJPARepository;

@Service
public class OrdersService {

	@Autowired
	OrdersJPARepository ordersRepo;
	
	public void saveOrderWithInvoice(Orders order){
		ordersRepo.save(order);
	}

}
