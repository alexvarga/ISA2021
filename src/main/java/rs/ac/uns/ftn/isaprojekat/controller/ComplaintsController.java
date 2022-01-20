package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.BoatComplaint;
import rs.ac.uns.ftn.isaprojekat.service.BoatComplaintService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@Controller
public class ComplaintsController {

    private final BoatComplaintService boatComplaintService;
    private final UserService userService;

    public ComplaintsController(BoatComplaintService boatComplaintService, UserService userService) {
        this.boatComplaintService = boatComplaintService;
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
    String answerToComplaint(Model model, @RequestParam(value = "complaintId") Long complaintId,
                             @RequestParam(value = "responseContent") String responseContent)
            throws UnsupportedEncodingException, MessagingException {


        BoatComplaint complaint = boatComplaintService.findById(complaintId);
        userService.sendComplaintResponseMail(responseContent, complaint.getContent(),
                complaint.getBoat().getOwner().getEmail(),
                complaint.getUser().getEmail(), " brod", complaint.getBoat().getName() );


        boatComplaintService.deleteById(complaintId);

        return showBoatComplaints(model);


    }
}
