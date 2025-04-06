package com.example.Online_FIR_System.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User SaveUser(User user) {
		return userRepo.save(user);
	}

	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.existsByUsername(username);
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public void updatePassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		User temp = userRepo.findByUsername(username);
		userRepo.deleteById(temp.getId());
		
		User user = new User();
		
		
		user.setId(temp.getId());
		user.setAddress(temp.getAddress());
		user.setName(temp.getName());
		user.setPassword(newPassword);
		user.setPhoneNumber(temp.getPhoneNumber());
		user.setUsername(temp.getUsername());		
		
		userRepo.save(user);
		
	}

	public String getName(String username) {
		return userRepo.findByUsername(username).getName();
	}

}
