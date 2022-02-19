package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/workspaces")
public class WorkspaceController {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserController userController;

    @GetMapping
    public @ResponseBody Iterable<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Workspace getWorkspaceById(@PathVariable Long id) {
        return workspaceRepository.existsById(id) ? workspaceRepository.findById(id).get() : null;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewWorkspace(@RequestParam String label, @RequestParam String color, @RequestParam char emoji, @RequestParam String userId) {
        User user = userController.getUserById(userId);
        if (user == null) {
            return String.format("User %s doesn't exist", userId);
        }

        Workspace workspace = new Workspace(label, color, emoji, user);
        workspaceRepository.save(workspace);
        return "Workspace successfully added";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteWorkspace(@PathVariable Long id) {
        if (workspaceRepository.existsById(id)) {
            workspaceRepository.deleteById(id);
            return String.format("Workspace %s successfully deleted", id);
        }
        return String.format("Workspace %s doesn't exist", id);
    }
}
