package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/projects")
@ResponseBody
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkspaceController workspaceController;

    @GetMapping
    public ResponseEntity<Iterable<Project>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.existsById(id)
                ? ResponseEntity.status(HttpStatus.OK).body(projectRepository.findById(id).get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewProject(@RequestParam String label, @RequestParam String color, @RequestParam String image, @RequestParam Long workspaceId) {
        Workspace workspace = workspaceController.getWorkspaceById(workspaceId).getBody();
        if (workspace == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Workspace %s doesn't exist", workspaceId));
        }

        Project project = new Project(label, color, image, workspace);
        projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.OK).body("Project successfully added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Project %s successfully deleted", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Project %s doesn't exist", id));
    }
}
