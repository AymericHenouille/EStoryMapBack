package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.repository.ProjectRepository;
import fr.miage.estorymap.utils.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public ResponseEntity<Iterable<Project>> getAllProjects() {
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) throws ProjectNotFoundException {
        final Optional<Project> project = projectRepository.findById(id);
        return project.map(ResponseEntity::ok).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectRepository.save(project));
    }

    @PutMapping("/projects")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) throws ProjectNotFoundException {
        final long id = project.getId();
        final Optional<Project> presentProject = projectRepository.findById(id);
        return presentProject.map((foundProject) -> ResponseEntity.ok(projectRepository.save(project)))
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

}
