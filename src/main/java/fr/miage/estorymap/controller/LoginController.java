package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.UserDTO;
import fr.miage.estorymap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.rowset.WebRowSet;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signup(WebRowSet request, Model model) {
        final UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "registration";
    }


}
