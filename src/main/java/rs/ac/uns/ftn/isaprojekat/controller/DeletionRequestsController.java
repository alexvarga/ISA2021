package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;
import rs.ac.uns.ftn.isaprojekat.service.DeletionRequestService;

import java.util.Set;

@Controller
public class DeletionRequestsController {

    private final DeletionRequestService deletionRequestService;

    public DeletionRequestsController(DeletionRequestService deletionRequestService) {
        this.deletionRequestService = deletionRequestService;
    }

    @GetMapping("/admin/deletion_requests")
    String showRequests(Model model){

        Set<DeletionRequest> requests = deletionRequestService.findAll();
        model.addAttribute("requests", requests);

        return "deletion_requests";
    }

    @PostMapping("/admin/deletion_requests")
    String accept(Model model, @RequestParam(value="reviewId") Long reviewId,  @RequestParam(value = "textAreaContent") String textAreaContent){

        Set<DeletionRequest> requests = deletionRequestService.findAll();

        model.addAttribute("requests", requests);

        System.out.println(textAreaContent);

        return "deletion_requests";
    }
    @PostMapping("/admin/deletion_requests/decline")
    String decline(Model model, @RequestParam(value="reviewId") Long reviewId, @RequestParam(value = "textAreaContent") String textAreaContent){

        Set<DeletionRequest> requests = deletionRequestService.findAll();
        model.addAttribute("requests", requests);

        System.out.println(textAreaContent);

        return "deletion_requests";
    }


}
