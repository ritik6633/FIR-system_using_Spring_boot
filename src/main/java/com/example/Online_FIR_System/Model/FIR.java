package com.example.Online_FIR_System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "fir_details")
public class FIR {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String state;
    private String district;
    private String policeStation;
	private String complaintType;
    private String details;
    private String complainantName;
    private String complainantPhone;
    private String status = "Pending";
	private Date currdate = new Date();

//	long millis=System.currentTimeMillis();
//	java.sql.Date date=new java.sql.Date(millis);

//	private LocalDate date = LocalDate.now();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getComplainantName() {
		return complainantName;
	}
	public void setComplainantName(String complainantName) {
		this.complainantName = complainantName;
	}
	public String getComplainantPhone() {
		return complainantPhone;
	}
	public void setComplainantPhone(String complainantPhone) {
		this.complainantPhone = complainantPhone;
	}
	
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public Date getDate() {
		return currdate;
	}
	public void setDate(Date date) {
		this.currdate = date;
	}

}
