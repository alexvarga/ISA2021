package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.BoatComplaint;
import rs.ac.uns.ftn.isaprojekat.model.InstructorComplaint;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseComplaint;
import rs.ac.uns.ftn.isaprojekat.service.BoatComplaintService;
import rs.ac.uns.ftn.isaprojekat.service.InstructorComplaintService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseComplaintService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@Controller
public class ComplaintsController {

    private final BoatComplaintService boatComplaintService;
    private final VacationHouseComplaintService vacationHouseComplaintService;
    private final InstructorComplaintService instructorComplaintService;
    private final UserService userService;

    public ComplaintsController(BoatComplaintService boatComplaintService, VacationHouseComplaintService vacationHouseComplaintService, InstructorComplaintService instructorComplaintService, UserService userService) {
        this.boatComplaintService = boatComplaintService;
        this.vacationHouseComplaintService = vacationHouseComplaintService;
        this.instructorComplaintService = instructorComplaintService;
        this.userService = userService;
    }

    @GetMapping("/admin/complaints")
    String showComplaintsHub(){


        return "admin_complaints_hub";
    }

    @GetMapping("/admin/complaints/boats")
    String showBoatComplaints(Model model){
        Set<BoatComplaint> complaints = boatComplaintService.findAll();

        model.addAttribute("complaints", complaints);

        return "complaints/boats_complaints";
    }

    @PostMapping("/admin/complaints/boats")
    String answerToBoatComplaint(Model model, @RequestParam(value = "complaintId") Long complaintId,
                             @RequestParam(value = "responseContent") String responseContent)
            throws UnsupportedEncodingException, MessagingException {


        BoatComplaint complaint = boatComplaintService.findById(complaintId);
        userService.sendComplaintResponseMail(responseContent, complaint.getContent(),
                complaint.getBoat().getOwner().getEmail(),
                complaint.getUser().getEmail(), " brod", complaint.getBoat().getName() );


        boatComplaintService.deleteById(complaintId);

        return showBoatComplaints(model);


    }

    @GetMapping("/admin/complaints/houses")
    String showHouseComplaints(Model model){
        Set<VacationHouseComplaint> complaints = vacationHouseComplaintService.findAll();

        model.addAttribute("complaints", complaints);

        return "complaints/houses_complaints";
    }

    @PostMapping("/admin/complaints/houses")
    String answerToHouseComplaint(Model model, @RequestParam(value = "complaintId") Long complaintId,
                             @RequestParam(value = "responseContent") String responseContent)
            throws UnsupportedEncodingException, MessagingException {


        VacationHouseComplaint complaint = vacationHouseComplaintService.findById(complaintId);
        userService.sendComplaintResponseMail(responseContent, complaint.getContent(),
                complaint.getVacationHouse().getVacationHouseOwner().getEmail(),
                complaint.getUser().getEmail(), "a vikendica", complaint.getVacationHouse().getName() );


        vacationHouseComplaintService.deleteById(complaintId);

        return showBoatComplaints(model);


    }

    @GetMapping("/admin/complaints/instructors")
    String showInstructorComplaints(Model model){
        Set<InstructorComplaint> complaints = instructorComplaintService.findAll();

        model.addAttribute("complaints", complaints);

        return "complaints/instructors_complaints";
    }

    @PostMapping("/admin/complaints/instructors")
    String answerToInstructorComplaint(Model model, @RequestParam(value = "complaintId") Long complaintId,
                                  @RequestParam(value = "responseContent") String responseContent)
            throws UnsupportedEncodingException, MessagingException {


        InstructorComplaint complaint = instructorComplaintService.findById(complaintId);
        userService.sendComplaintResponseMail(responseContent, complaint.getContent(),
                complaint.getInstructor().getEmail(),
                complaint.getUser().getEmail(), " profil", "");


        instructorComplaintService.deleteById(complaintId);

        return showBoatComplaints(model);


    }


}
