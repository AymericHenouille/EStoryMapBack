package fr.miage.estorymap.service;

import fr.miage.estorymap.component.CustomUserDetails;
import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.UserSensitive;
import fr.miage.estorymap.repository.UserRepository;
import fr.miage.estorymap.repository.UserSensitiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSensitiveRepository userSensitiveRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByEmail(username);
        final Optional<UserSensitive> userSensitive = user.flatMap(u -> userSensitiveRepository.findByUserId(u.getId()));
        if (user.isPresent() && userSensitive.isPresent())
            return new CustomUserDetails(user.get(), userSensitive.get());
        throw new UsernameNotFoundException(String.format("User %s not found", username));
    }

    public Optional<User> findUserByPrincipal(Principal principal) {
        final String username = principal.getName();
        return userRepository.findByEmail(username);
    }

    public User signupUser(User user, UserSensitive userSensitive) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final User savedUser = userRepository.save(user);
        userSensitive.bind(savedUser);
        userSensitive.encodePassword(encoder);
        userSensitiveRepository.save(userSensitive);
        return savedUser;
    }

}
