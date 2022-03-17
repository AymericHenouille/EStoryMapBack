package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.repository.ProjectRepository;
import fr.miage.estorymap.service.ProjectService;
import fr.miage.estorymap.service.WorkspaceService;
import fr.miage.estorymap.utils.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Objects;

@RestController
public class ProjectImageController {

    private static final String PROJECT_IMAGE_PATH = "projects/";

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects/{id}/cover")
    public ResponseEntity<Resource> serveFile(Principal principal, @PathVariable long id) throws ProjectNotFoundException, MalformedURLException {
        final Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        final Path path = project.getCover() != null
            ? Paths.get(PROJECT_IMAGE_PATH + "/" + id).resolve(project.getCover())
            : Paths.get(URI.create(Objects.requireNonNull(getClass().getClassLoader().getResource("default.jpg")).toString()));
        final Resource file = new UrlResource(path.toUri());
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + project.getCover() + "\"");
        return ResponseEntity.ok().headers(headers).body(file);
    }

    @PostMapping("/projects/{id}/cover")
    public @ResponseBody ResponseEntity<Project> uploadImage(Principal principal, @PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException, ProjectNotFoundException {
        final Path dest = Path.of(PROJECT_IMAGE_PATH + "/" + id + "/" + file.getOriginalFilename());
        Files.createDirectories(dest.getParent());
        final Project project = projectService.findProjectByIdForUser(principal, id);
        file.transferTo(dest.toAbsolutePath());
        project.setCover(file.getOriginalFilename());
        return ResponseEntity.ok(projectRepository.save(project));
    }

}
