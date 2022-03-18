package fr.miage.estorymap.service;

import fr.miage.estorymap.component.RegistrationRequest;
import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.UserSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public User register(RegistrationRequest request) {
        final User user = new User(request);
        final UserSensitive userSensitive = new UserSensitive(request);
        return userDetailsService.signupUser(user, userSensitive);
    }

}
