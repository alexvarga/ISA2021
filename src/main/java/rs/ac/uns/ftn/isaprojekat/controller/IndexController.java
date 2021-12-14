package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {



    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model){

        System.out.println("hello from index controller");


        return "index";
    }
}
