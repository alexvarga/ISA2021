package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reserve")
@Controller
public class ReservationProcessController {

    @PostMapping({"/", ""})
    String test(@Param(value="id") Long id, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd){
        System.out.println(id+" "+dateFrom + " "+ dateEnd);

        return "index";
    }


}
