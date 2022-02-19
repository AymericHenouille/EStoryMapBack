package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkspaceController workspaceController;

    @GetMapping
    public @ResponseBody Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Project getProjectById(@PathVariable Long id) {
        return projectRepository.existsById(id) ? projectRepository.findById(id).get() : null;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewProject(@RequestParam String label, @RequestParam String color, @RequestParam String image, @RequestParam Long workspaceId) {
        Workspace workspace = workspaceController.getWorkspaceById(workspaceId);
        if (workspace == null) {
            return String.format("Workspace %s doesn't exist", workspaceId);
        }

        Project project = new Project(label, color, image, workspace);
        projectRepository.save(project);
        return "Project successfully added";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return String.format("Project %s successfully deleted", id);
        }
        return String.format("Project %s doesn't exist", id);
    }
}
