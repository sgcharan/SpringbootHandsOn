package com.sampleProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampleProject.entity.Userlist;
import com.sampleProject.repository.UserlistJPARepository;

@Service
public class UserlistService {

	@Autowired
	UserlistJPARepository userlistRepo;
	
	public void addUsers(Userlist userlist){
		userlistRepo.save(userlist);
	}
	
	public List<Userlist> fetchOtherUsers(String username){
		
		return userlistRepo.findByUsername(username);
	}
}
