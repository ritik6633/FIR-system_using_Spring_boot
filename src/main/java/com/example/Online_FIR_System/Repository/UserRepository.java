package com.example.Online_FIR_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByUsername(String username);

	User findByUsername(String username);

}
