package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkspaceController {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserController userController;

    @GetMapping("/workspaces")
    public ResponseEntity<Iterable<Workspace>> getAllWorkspaces() {
        return ResponseEntity.status(HttpStatus.OK).body(workspaceRepository.findAll());
    }

    @GetMapping("/workspaces/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable Long id) {
        return workspaceRepository.existsById(id)
                ? ResponseEntity.status(HttpStatus.OK).body(workspaceRepository.findById(id).get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/workspaces/add")
    public ResponseEntity<String> addNewWorkspace(@RequestParam String label, @RequestParam String color, @RequestParam char emoji, @RequestParam String userId) {
        User user = userController.getUserById(userId).getBody();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User %s doesn't exist %n", userId));
        }

        Workspace workspace = new Workspace(label, color, emoji, user);
        workspaceRepository.save(workspace);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Workspace successfully added %n"));
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<String> deleteWorkspace(@PathVariable Long id) {
        if (workspaceRepository.existsById(id)) {
            workspaceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Workspace %s successfully deleted %n", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Workspace %s doesn't exist %n", id));
    }
}
