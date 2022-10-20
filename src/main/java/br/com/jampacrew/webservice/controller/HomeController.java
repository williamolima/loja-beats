package br.com.jampacrew.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String showHomePage(Model m) {
        m.addAttribute("menu", "home");
        return "index";
    }
}