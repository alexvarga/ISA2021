//package rs.ac.uns.ftn.isaprojekat.controller;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import rs.ac.uns.ftn.isaprojekat.model.Adventure;
//import rs.ac.uns.ftn.isaprojekat.service.AdventureService;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@RequestMapping("/search")
//@Controller
//public class SearchAdventureController {
//
//    private final AdventureService adventureService;
//
//    public SearchAdventureController(AdventureService adventureService) {
//        this.adventureService = adventureService;
//    }
//
//    @PostMapping("/adventures")
//    String listAdventureResluts(Model model, @Param(value = "dateFrom") String dateFrom, @Param(value="dateEnd") String dateEnd){
//
//
//        return listAdventureResultsByPage(model,1, "id", "asc", dateFrom, dateEnd);
//    }
//
//    @GetMapping("/adventures/page/{pageNumber}")
//    String listAdventureResultsByPage(Model model, @PathVariable("pageNumber") int currentPage,
//                                 @Param(value="sortField") String sortField,
//                                 @Param(value="sortDirection") String sortDirection,
//                                 @Param(value="dateFrom") String dateFrom,
//                                 @Param(value="dateEnd") String dateEnd){
//
//
//        if (sortField==null){sortField="id";}
//        if(sortDirection==null){sortDirection="asc";}
//
//
//        model.addAttribute("dateFrom", dateFrom);
//        model.addAttribute("dateEnd", dateEnd);
//        System.out.println(dateFrom + "+++" + dateEnd);
//
//
//        Page<Adventure> page = adventureService.findAdventureNotReserved(currentPage, sortField, sortDirection, LocalDateTime.parse(dateFrom+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), LocalDateTime.parse(dateEnd+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//        List<Adventure> listAdventures = page.getContent();
//
//        Long numberOfElements = page.getTotalElements();
//        int numberOfPages = page.getTotalPages();
//
//        model.addAttribute("search", true);
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("numberOfElements", numberOfElements);
//        model.addAttribute("numberOfPages", numberOfPages);
//        model.addAttribute("adventures", listAdventures);
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDirection", sortDirection);
//
//        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
//        model.addAttribute("reverseSortDirection", reverseSortDirection);
//
//
//
//        return "adventures";
//    }
//}
