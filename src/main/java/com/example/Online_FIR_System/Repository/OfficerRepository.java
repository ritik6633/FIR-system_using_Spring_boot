package com.example.Online_FIR_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.Officer;
import org.springframework.stereotype.Repository;

import java.util.List;
//import java.util.List;


@Repository
public interface OfficerRepository extends JpaRepository<Officer, Long> {
	List<Officer> findByState(String state);
	List<Officer> findByDist(String dist);
	List<Officer> findByCity(String city);
	List<Officer> findByPoliceStation(String policeStation);

	boolean existsByUsername(String username);

	Officer findByUsername(String serviceNumberString);
}

