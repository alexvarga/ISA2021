package rs.ac.uns.ftn.isaprojekat.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;
import rs.ac.uns.ftn.isaprojekat.service.DeletionRequestService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@Controller
public class DeletionRequestsController {

    private final DeletionRequestService deletionRequestService;
    private final UserService userService;

    public DeletionRequestsController(DeletionRequestService deletionRequestService, UserService userService) {
        this.deletionRequestService = deletionRequestService;
        this.userService = userService;
    }

    @GetMapping("/admin/deletion_requests")
    String showRequests(Model model){

        Set<DeletionRequest> requests = deletionRequestService.findAll();
        model.addAttribute("requests", requests);

        return "deletion_requests";
    }

    @PostMapping("/admin/deletion_requests")
    String accept(Model model, @RequestParam(value="requestId") Long requestId,  @RequestParam(value = "textAreaContent") String textAreaContent) throws UnsupportedEncodingException, MessagingException {

        DeletionRequest request = deletionRequestService.findById(requestId);

        userService.delete(request.getUser());

        userService.sendRequestAcceptedEmail(textAreaContent, request.getUser().getEmail());



        return showRequests(model);
    }
    @PostMapping("/admin/deletion_requests/decline")
    String decline(Model model, @RequestParam(value="requestId") Long requestId, @RequestParam(value = "textAreaContent") String textAreaContent) throws UnsupportedEncodingException, MessagingException {

        DeletionRequest request = deletionRequestService.findById(requestId);

        userService.sendRequestDeniedEmail(textAreaContent, request.getUser().getEmail());

        deletionRequestService.deleteById(requestId);

        return showRequests(model);
    }


}
