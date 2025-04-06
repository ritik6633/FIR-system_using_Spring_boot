package com.example.Online_FIR_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Online_FIR_System.Model.ComplaintStatus;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
    ComplaintStatus findByComplaintId(Long complaintId);
}
