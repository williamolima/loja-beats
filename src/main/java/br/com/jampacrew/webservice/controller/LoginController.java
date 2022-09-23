package br.com.jampacrew.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/form")
    public String showLoginForm() {
        return "cadastro";
    }

    @RequestMapping("/valide")
    public String valideLogin(String login, String senha, Model model) {
        if (login.equals("admin") && senha.equals("secret")) {
            model.addAttribute("login", "login");
            return "index";
        } else {
            model.addAttribute("erro", "Login inváldo!");
            return "cadastro";
        }
    }
}
