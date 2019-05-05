package ua.net.kurpiak.commoditycirculation.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/products"}, method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "/in-out", method = RequestMethod.GET)
    public String inOut() {
        return "in_out";
    }
}
