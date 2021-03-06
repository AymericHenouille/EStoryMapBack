package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.repository.UserRepository;
import fr.miage.estorymap.service.CustomUserDetailsService;
import fr.miage.estorymap.utils.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Principal principal) {
        final Optional<User> user = userRepository.findByEmail(principal.getName());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(Principal principal, @RequestBody User user) {
        final Optional<User> oldUser = userRepository.findByEmail(principal.getName());
        oldUser.ifPresent(value -> value.update(user));
        return oldUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Boolean> getUserByEmail(Principal principal, @PathVariable String email) throws UserNotFoundException {
        final Optional<User> user = userDetailsService.findUserByPrincipal(principal);
        return user.map(value -> userRepository.findByEmail(email).isPresent())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

}
