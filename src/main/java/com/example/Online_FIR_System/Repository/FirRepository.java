package com.example.Online_FIR_System.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.FIR;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FirRepository  extends JpaRepository<FIR , Long > {
	Optional<FIR> findById(Long id);
	List<FIR> findByPoliceStation(String policeStation);

    List<FIR> findByState(String state);

	List<FIR> findByDistrict(String district);

	List<Object[]> countDistinctByComplaintType(String district);
	List<Object[]> countStateByComplaintType(String state);
	List<Object[]> countPoliceStationByComplaintType(String policeStation);

	@Query(value = "SELECT complaint_type, COUNT(*) " +
			"FROM fir_details " +
			"WHERE police_station = :policeStation " +
			"GROUP BY complaint_type",
			nativeQuery = true)
	List<Object[]> countComplaintTypeByPoliceStation(@Param("policeStation") String policeStation);

	@Query(value = "SELECT complaint_type, COUNT(*) " +
			"FROM fir_details " +
			"WHERE state = :state " +
			"GROUP BY complaint_type",
			nativeQuery = true)
	List<Object[]> countComplaintTypeByState(@Param("state") String state);

	@Query(value = "SELECT complaint_type, COUNT(*) " +
			"FROM fir_details " +
			"WHERE district = :district " +
			"GROUP BY complaint_type",
			nativeQuery = true)
	List<Object[]> countComplaintTypeByDistrict(@Param("district") String district);

	@Query(value = "SELECT complaint_type, COUNT(*) " +
			"FROM fir_details " +
			"GROUP BY complaint_type",
			nativeQuery = true)
	List<Object[]> countAllByComplaintType();


	//List<Object[]> countAllByComplaintType();

}
