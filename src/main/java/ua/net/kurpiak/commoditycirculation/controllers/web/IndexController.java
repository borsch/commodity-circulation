package ua.net.kurpiak.commoditycirculation.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "redirect:/products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products() {
        return "index";
    }

    @RequestMapping(value = "/income", method = RequestMethod.GET)
    public String income() {
        return "income";
    }

    @RequestMapping(value = "/outcome", method = RequestMethod.GET)
    public String outcome() {
        return "outcome";
    }
}


