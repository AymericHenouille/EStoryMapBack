package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userRepository.existsById(id)
                ? ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id).get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/users/add")
    public ResponseEntity<String> addNewUser(@RequestParam String idu, @RequestParam String mail) {
        if (userRepository.existsById(idu)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("User %s already exists %n", idu));
        }
        User user = new User(idu, mail);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("User successfully saved %n"));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("User %s successfully deleted %n", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User %s doesn't exist %n", id));
    }
}
