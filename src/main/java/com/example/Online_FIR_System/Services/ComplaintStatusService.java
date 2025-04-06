package com.example.Online_FIR_System.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Online_FIR_System.Model.ComplaintStatus;
import com.example.Online_FIR_System.Repository.ComplaintStatusRepository;

@Service
public class ComplaintStatusService {

    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;

    public void saveComplaintStatus(ComplaintStatus complaintStatus) {
        complaintStatusRepository.save(complaintStatus);
    }

    public ComplaintStatus findByComplaintId(Long complaintId) {
        return complaintStatusRepository.findByComplaintId(complaintId);
    }
}
