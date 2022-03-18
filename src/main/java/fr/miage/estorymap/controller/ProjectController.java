package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.ProjectRepository;
import fr.miage.estorymap.service.ProjectService;
import fr.miage.estorymap.utils.exception.ProjectNotFoundException;
import fr.miage.estorymap.utils.exception.WorkspaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<Iterable<Project>> getAllProjects(Principal principal) {
        return ResponseEntity.ok(projectService.findUserProjects(principal));
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(Principal principal, @PathVariable Long id) throws ProjectNotFoundException {
        return ResponseEntity.ok(projectService.findUserProjects(principal, id));
    }

    @GetMapping("/projects/{id}/workspace")
    public ResponseEntity<Workspace> getProjectWorkspace(Principal principal, @PathVariable Long id) throws ProjectNotFoundException, WorkspaceNotFoundException {
        final Project project = projectService.findUserProjects(principal, id);
        final Workspace workspace = projectService.findProjectWorkspace(project);
        return ResponseEntity.ok(workspace);
    }

    @PostMapping("/projects/{id}")
    public ResponseEntity<Project> createNewProject(Principal principal, @PathVariable Long id, @RequestBody Project project) throws WorkspaceNotFoundException {
        return ResponseEntity.ok(projectService.createProject(principal, id, project));
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(Principal principal, @PathVariable Long id, @RequestBody Project project) throws ProjectNotFoundException, WorkspaceNotFoundException {
        return ResponseEntity.ok(projectService.updateProject(principal, id, project));
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(Principal principal, @PathVariable Long id) throws ProjectNotFoundException {
        final Project project = projectService.findUserProjects(principal, id);
        projectService.deleteProject(project);
        return ResponseEntity.ok().build();
    }

}
