package fr.miage.estorymap.controller;

import fr.miage.estorymap.component.RegistrationRequest;
import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @PostMapping("registration")
    public User register(@RequestBody RegistrationRequest request) {
        return service.register(request);
    }

}
