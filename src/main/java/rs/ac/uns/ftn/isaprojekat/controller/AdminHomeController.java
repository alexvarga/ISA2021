package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

    @GetMapping("/adminPage")
    public String adminPage(){

        return "adminPage";
    }

}
