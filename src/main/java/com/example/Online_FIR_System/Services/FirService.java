package com.example.Online_FIR_System.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_FIR_System.Model.ComplaintStatus;
import com.example.Online_FIR_System.Model.FIR;
import com.example.Online_FIR_System.Repository.FirRepository;

@Service
public class FirService {
	
	@Autowired
	private FirRepository FirRepo;
	
	@Autowired
    private ComplaintStatusService complaintStatusService;
	
	public FIR SaveFir(FIR fir){
		return FirRepo.save(fir);
	}
	
	public List<FIR> getAllFIR(){
		return FirRepo.findAll();
	}
	
	public void acceptComplaint(Long complaintId) {
        FIR complaint = FirRepo.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
        complaint.setStatus("Accepted");
        FirRepo.save(complaint);
    }

    public void rejectComplaint(Long complaintId) {
        FIR complaint = FirRepo.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
        complaint.setStatus("Rejected");
        FirRepo.save(complaint);
    }

    public Optional<FIR> findById(Long id) {
        return FirRepo.findById(id);
    }
    public void deleteById(Long id) {
        FirRepo.deleteById(id); // Delete the complaint by ID
    }
    
    	public List<FIR> findByPoliceStation(String policeStation) {
        return FirRepo.findByPoliceStation(policeStation);
    }

//    public List<FIR> findAllPendingFir(String status , String policeStation) {
//        return FirRepo.findAllPendingFir(status , policeStation);
//    }
    	
    	public void updateFirStatus(Long complaintId, String status) {
            Optional<ComplaintStatus> complaintStatus = Optional.ofNullable(complaintStatusService.findByComplaintId(complaintId));
            if (complaintStatus.isPresent()) {
                ComplaintStatus cs = complaintStatus.get();
                cs.setStatus(status);
                complaintStatusService.saveComplaintStatus(cs);
            }
        }

		public void arrestedCulprit(Long complaintId) {
			// TODO Auto-generated method stub
			FIR complaint = FirRepo.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
	        complaint.setStatus("Culprit Arrested");
	        FirRepo.save(complaint);
			
		}

        public void updateFirRemarks(Long complaintId, String remarks) {
            Optional<ComplaintStatus> complaintStatus = Optional.ofNullable(complaintStatusService.findByComplaintId(complaintId));
            if (complaintStatus.isPresent()) {
                ComplaintStatus cs = complaintStatus.get();
                cs.setRemarks(remarks);
                complaintStatusService.saveComplaintStatus(cs);
            }
        }

        public void updateFirOfficerAssigned(Long complaintId, String officerAssigned) {
            Optional<ComplaintStatus> complaintStatus = Optional.ofNullable(complaintStatusService.findByComplaintId(complaintId));
            if (complaintStatus.isPresent()) {
                ComplaintStatus cs = complaintStatus.get();
                cs.setOfficerAssigned(officerAssigned);
                complaintStatusService.saveComplaintStatus(cs);
            }
        }

    public List<FIR> findByState(String state) {
        return FirRepo.findByState(state);
    }

    public List<FIR> findByDistrict(String district) {
        return FirRepo.findByDistrict(district);
    }



    public Map<String, Long> countComplaintTypeByDistrict(String district) {
        List<Object[]> data = FirRepo.countComplaintTypeByDistrict(district);
        Map<String, Long> result = new HashMap<>();
        for (Object[] obj : data) {
            if(obj != null && obj.length >= 2 && obj[0] != null && obj[1] != null) {
                String complaintType = String.valueOf(obj[0]);
                Long count = ((Number) obj[1]).longValue();
                result.put(complaintType, count);
            }
        }
        return result;
    }


    public Map<String, Long> countComplaintTypeByState(String state) {
        List<Object[]> data = FirRepo.countComplaintTypeByState(state);
        Map<String, Long> result = new HashMap<>();
        for (Object[] obj : data) {
            if(obj != null && obj.length >= 2 && obj[0] != null && obj[1] != null) {
                String complaintType = String.valueOf(obj[0]);
                Long count = ((Number) obj[1]).longValue();
                result.put(complaintType, count);
            }
        }
        return result;
    }

    public Map<String, Long> countComplaintTypeByPoliceStation(String policeStation) {
        List<Object[]> data = FirRepo.countComplaintTypeByPoliceStation(policeStation);
        Map<String, Long> result = new HashMap<>();
        for (Object[] obj : data) {
            if(obj != null && obj.length >= 2 && obj[0] != null && obj[1] != null) {
                String complaintType = String.valueOf(obj[0]);
                Long count = ((Number) obj[1]).longValue();
                result.put(complaintType, count);
            }
        }
        return result;
    }


    public Map<String, Long> countAllByComplaintType() {
        List<Object[]> data = FirRepo.countAllByComplaintType();
        Map<String , Long> result = new HashMap<>();
        for (Object[] obj : data) {
            result.put((String) obj[0], (Long) obj[1]);
        }
        return result;
    }
}
