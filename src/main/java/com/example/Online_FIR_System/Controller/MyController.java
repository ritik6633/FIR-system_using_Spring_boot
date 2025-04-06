package com.example.Online_FIR_System.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Online_FIR_System.Model.ComplaintStatus;
import com.example.Online_FIR_System.Model.FIR;
import com.example.Online_FIR_System.Model.Officer;
import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Services.ComplaintStatusService;
import com.example.Online_FIR_System.Services.FirService;
import com.example.Online_FIR_System.Services.OfficerService;
import com.example.Online_FIR_System.Services.UserService;





@Controller
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FirService firService;
	
	@Autowired
	private OfficerService officerService; 
	
	@Autowired
    private ComplaintStatusService complaintStatusService;
	
	boolean isLogin  = false;
	boolean isOfficer = false;
	
	
	String activePoliceStation = "";
	String activeUser = "";
	int noOfFir = 0;
	
	@GetMapping("/")
	public ModelAndView loadHome() {
		ModelAndView modelAndView = new ModelAndView();
		
		if(isLogin) {
			if(isOfficer) {
				modelAndView.addObject("activeUser", activeUser);
				modelAndView.addObject("noOfFir" , String.valueOf(noOfFir));
				modelAndView.setViewName("officerLoggedin");				
			}
			else {
				modelAndView.addObject("activeUser", activeUser);
				modelAndView.setViewName("loggedin");
				return modelAndView;
			}
		}
		else {
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}
	
	@GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login"); // This should match the name of your JSP file without the .jsp extension
    }
	
	@GetMapping("/signup")
	public ModelAndView showSignUp() {
		return new ModelAndView("sign-up");
	}
	
	@GetMapping("/loggedin")
	public ModelAndView showLoggedPage() {
		ModelAndView modelAndView = new ModelAndView();
		if(isLogin) {
			modelAndView.addObject("activeUser", activeUser);
			modelAndView.setViewName("loggedin");
		}
		else {
			//modelAndView.addObject("activeUser", "Username");
			modelAndView.setViewName("home");
		}
		return modelAndView;	
	}
	
	
	
	
	@GetMapping("/add-complaint")
	public ModelAndView showAddComplaint() {
		if(isLogin) {
			return new ModelAndView("add-complaint");
		}
		else {
			return new ModelAndView("login");
		}
	}
	
	
	@PostMapping("/admin/FIRReport")
	public ModelAndView AllFir(@RequestParam("state") String state , @RequestParam("district") String district , @RequestParam("policeStation") String ps) {
		ModelAndView modelAndView = new ModelAndView();
		List<FIR> complaints = new ArrayList<>();
		if(state.equals("All")){
			complaints = firService.getAllFIR();
		}
		else if(district.equals("All")) {
			complaints = firService.findByState(state);
		}
		else if (ps.equals("All")) {
			complaints = firService.findByDistrict(district);
		}

		else {
			complaints = firService.findByPoliceStation(ps);
		}
		modelAndView.addObject("complaints", complaints);
        modelAndView.setViewName("allFIR");

		return modelAndView;
	}
	
	
	@GetMapping("/admin/user")
	public ModelAndView AllUser() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = userService.getAllUser();
		modelAndView.addObject("users", users);
		modelAndView.setViewName("alluser");
		return modelAndView;	
	}
	
	
	@PostMapping("/admin/OfficerReport")
	public ModelAndView AllOfficer(@RequestParam("state") String state , @RequestParam("district") String district , @RequestParam("city") String city , @RequestParam("policeStation") String ps) {
		ModelAndView modelAndView = new ModelAndView();
		List<Officer> officers = new ArrayList<Officer>();
		if(state.equals("All")){
			officers = officerService.getAllOfficer();
		}
		else if(district.equals("All")) {
			officers = officerService.findByState(state);
		}
		else if(city.equals("All")) {
			officers = officerService.findByDistrict(district);
		}
		else if(ps.equals("All")) {
			officers = officerService.findByCity(city);
		}
		else {
			officers = officerService.findByPoliceStation(ps);
		}
//		List<Officer> officers = officerService.getAllOfficer();
		modelAndView.addObject("officers", officers);
		modelAndView.setViewName("allofficer");
		return modelAndView;
	}
	
	@GetMapping("/forgotpassword")
	public ModelAndView showForgotPassword() {
		return new ModelAndView("forgotpassword");
	}
	
	
	
//	@GetMapping("/login/officer")
//	public ModelAndView showOfficerHome() {
//		return new ModelAndView("login");
//	}
//	@GetMapping("/signupOfficer")
//	public ModelAndView showOfficerSignup() {
//		return new ModelAndView("signupOfficer");
//	}

	@GetMapping("/admin/dashboard")
	public ModelAndView showAdminDashboard() {
		return new ModelAndView("adminHome");
	}
	
	
	
	
	@PostMapping("/signup/client")
    public ModelAndView registerUser(
            @RequestParam("name") String name,
            @RequestParam("phone") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            ModelAndView modelAndView) {
        if (userService.existsByUsername(username)) {
            modelAndView.addObject("errorMessage", "Username already exists");
            modelAndView.setViewName("sign-up");
            return modelAndView;
        }
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password);

        userService.SaveUser(user);

        modelAndView.addObject("message", "Registration successful! Please login.");
        modelAndView.setViewName("login");
        return modelAndView;
    }
	
	
	@PostMapping("/signup/officer")
	public ModelAndView registerOfficer(@RequestParam("serviceNumber") String Service,
			@RequestParam("name") String name , @RequestParam("email") String mail,
			@RequestParam("phone") String phone ,@RequestParam("state") String state ,
			@RequestParam("district") String dist ,@RequestParam("city") String ct,
			@RequestParam("policeStationName") String police ,@RequestParam("username") String user ,
			@RequestParam("password") String pass , ModelAndView modelAndView) {
		
		if(officerService.existsByUsername(name)) {
			modelAndView.addObject("errorMessage", "Username already exists");
            modelAndView.setViewName("sign-up");
            return modelAndView;
		}
		
		Officer officer = new Officer();
		officer.setCity(ct);
		officer.setDist(dist);
		officer.setEmail(mail);
		officer.setName(name);
		officer.setPassword(pass);
		officer.setPhone(phone);
		officer.setPoliceStation(police);
		officer.setServiceNumber(Service);
		officer.setState(state);
		officer.setUsername(user);
		
		officerService.SaveOfficer(officer);
		modelAndView.addObject("message", "Registration successful! Please login.");
        modelAndView.setViewName("login");
		return modelAndView;		
	}
	
	
	@PostMapping("/forgotpassword")
	public ModelAndView updatePassword(@RequestParam("username") String username , 
			@RequestParam("newPassword") String newPassword , @RequestParam("confirmPassword") String cnfPassword , ModelAndView modelAndView ) {
		User user = userService.findByUsername(username);
		Officer officer = officerService.findByUsername(username);
		
		
		if(user != null) {
			//user.setPassword(newPassword);
			userService.updatePassword(username,newPassword);
			modelAndView.setViewName("login");
		}
		else if(officer != null) {
			//officer.setPassword(newPassword);
			officerService.updatePassword(username , newPassword);
			modelAndView.setViewName("login");
		}
		else {
			modelAndView.setViewName("WrongUsername");
		}		
		return modelAndView;
		
	}
	
	
	@PostMapping("/login/client")
    public ModelAndView loginUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findByUsername(username);
        if (user == null || !(userService.existsByUsername(username))) {
        	modelAndView.addObject("errorMessage", "Invalid username");
            modelAndView.setViewName("WrongUsername");
        } 
        else if (!user.getPassword().equals(password)) {
        	modelAndView.addObject("errorPassword", "Wrong Password");
        	modelAndView.setViewName("WrongPassword");
        }
        else {
        	//session.setAttribute("username", username);
        	isLogin = true;
        	activeUser = userService.getName(username);
        	//modelAndView.setViewName("home"); // Redirect to the home page on successful login
        	modelAndView.addObject("activeUser", activeUser);        	
        	modelAndView.setViewName("loggedin");
        }

        return modelAndView;
    }
	
	
	@PostMapping("/login/officer")
	public ModelAndView loginOfficer(@RequestParam("policeServiceNumber") String serviceNumberString , 
			@RequestParam("password") String password , ModelAndView modelAndView) {
		
		Officer officer = officerService.findByUsername(serviceNumberString);
		
		if (officer == null || !(officerService.existsByUsername(serviceNumberString))){
        	modelAndView.addObject("errorMessage", "Invalid username");
            modelAndView.setViewName("WrongUsername");
        } 
        else if (!officer.getPassword().equals(password)) {
        	modelAndView.addObject("errorPassword", "Wrong Password");
        	modelAndView.setViewName("WrongPassword");
        }
        else {
        	//session.setAttribute("username", serviceNumberString);
        	isLogin = true;
        	isOfficer = true;
        	activeUser = officerService.getOfficerName(serviceNumberString);
        	activePoliceStation = officer.getPoliceStation();
        	//List<FIR> complaints = firService.getAllFIR();
			if(noOfFir == 0) {
				List<FIR> complaints = firService.findByPoliceStation(activePoliceStation);
				for (int i = 0; i < complaints.size(); i++) {
					if (complaints.get(i).getStatus().equals("Pending")) {
						noOfFir += 1;
					}
				}
			}
			//noOfFir = complaints.size();
//        	modelAndView.addObject("complaints", complaints);
//            modelAndView.setViewName("officerHome"); // Redirect to the home page on successful login
			modelAndView.addObject("noOfFir" , String.valueOf(noOfFir));
        	modelAndView.addObject("activeUser", activeUser);

        	modelAndView.setViewName("officerLoggedin");
        }
		
		return modelAndView;	
		
	}

	@PostMapping("/login/admin")
	public ModelAndView AdminLogin(@RequestParam("username") String username , @RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView();
		if(username.equals("admin") && password.equals("admin")) {
			modelAndView.setViewName("adminHome");
		}
		else {
			modelAndView.setViewName("WrongUsername");
		}
		return modelAndView;
	}
	
	
	
	
	@PostMapping("/add-complaint")
	public ModelAndView addComplaint(
			@RequestParam("state") String state,
            @RequestParam("district") String district,
            @RequestParam("policeStation") String policeStation,
            @RequestParam("details") String details,
            @RequestParam("complainantName") String complainantName,
			@RequestParam("complaintType") String complaintType,
            @RequestParam("complainantPhone") String complainantPhone) {
		
		ModelAndView modelAndView = new ModelAndView();

		FIR complaint = new FIR();
		complaint.setState(state);
        complaint.setDistrict(district);
        complaint.setPoliceStation(policeStation);
		complaint.setComplaintType(complaintType);
        complaint.setDetails(details);
        complaint.setComplainantName(complainantName);
        complaint.setComplainantPhone(complainantPhone);

        FIR savedComplaint = firService.SaveFir(complaint);

        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setComplaintId(savedComplaint.getId());
        complaintStatus.setStatus(savedComplaint.getStatus());

        complaintStatusService.saveComplaintStatus(complaintStatus);

        //modelAndView.addObject("successMessage", "Complaint submitted successfully!");
        modelAndView.addObject("savedComplaint", savedComplaint);       
        
        modelAndView.setViewName("complaintSummary");
		
		return modelAndView;
	}
	
	
	
	@GetMapping("/officerHome")
    public ModelAndView officerHome() {
        ModelAndView modelAndView = new ModelAndView();
        //List<FIR> complaints = firService.getAllFIR();
        List<FIR> complaints = firService.findByPoliceStation(activePoliceStation);
        modelAndView.addObject("complaints", complaints);
        modelAndView.setViewName("officerHome");
        return modelAndView;
    }

	@PostMapping("/acceptComplaint")
    public String acceptComplaint(@RequestParam("complaintId") Long complaintId) {
        FIR complaint = firService.findById(complaintId).orElse(null);
        if (complaint != null) {
            complaint.setStatus("Accepted");
            firService.SaveFir(complaint);
            firService.updateFirStatus(complaintId, "Accepted");
			firService.updateFirOfficerAssigned(complaintId , activeUser);
        }
        return "redirect:/officerHome";
    }

	@PostMapping("/rejectComplaint")
    public String rejectComplaint(@RequestParam("complaintId") Long complaintId) {
        FIR complaint = firService.findById(complaintId).orElse(null);
        if (complaint != null) {
            //firService.deleteById(complaintId);
            firService.rejectComplaint(complaintId);
            firService.updateFirStatus(complaintId, "Rejected");
			firService.updateFirOfficerAssigned(complaintId , activeUser);
        }
        return "redirect:/officerHome";
    }

	@GetMapping("/admin/reports")
	public ModelAndView showReport(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("reports");
		return mv;
	}

	@PostMapping("/generateReport")
	public ModelAndView showComplaintReport(
			@RequestParam("state") String state,
			@RequestParam("district") String district,
			@RequestParam("policeStation") String policeStation){
		ModelAndView mv = new ModelAndView();

		Map<String , Long> complaints = new HashMap<>();
		if(state.equals("All")){
			complaints = firService.countAllByComplaintType();
		}
		else if(district.equals("All")) {
			complaints = firService.countComplaintTypeByState(state);
		}
		else if (policeStation.equals("All")) {
			complaints = firService.countComplaintTypeByDistrict(district);
		}
		else {
			complaints = firService.countComplaintTypeByPoliceStation(policeStation);
		}
		mv.addObject("complaints", complaints);

		mv.setViewName("showReport");
		return mv;
	}

	@GetMapping("/admin/officers")
	public ModelAndView showOfficerReport(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("officerReport");
		return mv;
	}

	@GetMapping("/admin/fir")
	public ModelAndView showFirReport(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("firReport");
		return mv;
	}

	@PostMapping("/addRemarks")
	public String updateRemarks(@RequestParam("complaintId") Long complaintId, @RequestParam("remarks") String remarks) {
		firService.updateFirRemarks(complaintId, remarks);
		return "redirect:/officerHome";
	}
	
	
	@GetMapping("/track-complaint")
    public ModelAndView showTrackComplaintForm() {
		if(isLogin) {
			return new ModelAndView("track-complaint-form");
		}
		else {
			return new ModelAndView("login");
		}	
    }

	@PostMapping("/trackComplaint")
	public ModelAndView trackComplaint(@RequestParam("complaintId") Long complaintId) {
		ModelAndView modelAndView = new ModelAndView("track-complaint");

		ComplaintStatus complaintStatus = complaintStatusService.findByComplaintId(complaintId);

		if (complaintStatus == null) {
			modelAndView.addObject("errorMessage", "No complaint found with the provided ID.");
		} else {
			String officerAssigned = complaintStatus.getOfficerAssigned();
			modelAndView.addObject("officerAssigned", officerAssigned != null ? officerAssigned : "No officer assigned yet.");
			FIR fir_details = firService.findById(complaintStatus.getComplaintId()).orElse(null);
			modelAndView.addObject("fir_details", fir_details);
			modelAndView.addObject("statusMessage", "The status of your complaint is: " + complaintStatus.getStatus());
			modelAndView.addObject("remarks", "Remarks: " + complaintStatus.getRemarks());
		}

		return modelAndView;
	}


	@GetMapping("/logout")
    public ModelAndView logout() {
        // Invalidate the session
    	isLogin = false;
    	isOfficer = false;
    	activeUser = "";
		noOfFir = 0;
    	return new ModelAndView("home");
   }
}
