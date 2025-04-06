package com.example.Online_FIR_System.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "complaint_status")
public class ComplaintStatus {

    // This class represents the status of a complaint
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long complaintId;
    private String status;
    private String remarks;
    private String officerAssigned = "Not Assigned";

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
    public String getRemarks() {
        // TODO Auto-generated method stub
        return remarks;
    }
    public void setRemarks(String remarks) {
        // TODO Auto-generated method stub
        this.remarks = remarks;
    }

    public void setOfficerAssigned(String officerAssigned) {
        // TODO Auto-generated method stub
        this.officerAssigned = officerAssigned;
    }

    public String getOfficerAssigned() {
        // TODO Auto-generated method stub
        return officerAssigned;
    }
}

