package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody User getUserById(@PathVariable String id) {
        return userRepository.existsById(id) ? userRepository.findById(id).get() : null;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String idu, @RequestParam String mail) {
        User user = new User(idu, mail);
        userRepository.save(user);
        return "User successfully saved";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return String.format("User %s successfully deleted", id);
        }
        return String.format("User %s doesn't exist", id);
    }
}
