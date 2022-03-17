package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.repository.ProjectRepository;
import fr.miage.estorymap.service.ProjectService;
import fr.miage.estorymap.utils.exception.FileNotFoundException;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@RestController
public class ProjectFileController {

    private static final String PROJECT_FILE_PATH = "projects";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects/{id}/bpmn")
    public ResponseEntity<Resource> serveBpmnFile(@PathVariable long id) throws ProjectNotFoundException, MalformedURLException, FileNotFoundException {
        final Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        final Path path = project.getBpmnName() != null
                ? Paths.get(PROJECT_FILE_PATH + "/" + id).resolve("bpmn-" + project.getBpmnName())
                : null;
        if (path == null) throw new FileNotFoundException(id, "BPMN");
        final Resource file = new UrlResource(path.toUri());
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + project.getBpmnName() + "\"");
        return ResponseEntity.ok().headers(headers).body(file);
    }

    @PostMapping("/projects/{id}/bpmn")
    public @ResponseBody
    ResponseEntity<Project> uploadBpmn(Principal principal, @PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException, ProjectNotFoundException {
        final Path dest = Path.of(PROJECT_FILE_PATH + "/" + id + "/bpmn-" + file.getOriginalFilename());
        Files.createDirectories(dest.getParent());
        final Project project = projectService.findProjectByIdForUser(principal, id);
        file.transferTo(dest.toAbsolutePath());
        project.setBpmnName(file.getOriginalFilename());
        return ResponseEntity.ok(projectRepository.save(project));
    }

    @GetMapping("/projects/{id}/mcd")
    public ResponseEntity<Resource> serveMcdFile(@PathVariable long id) throws ProjectNotFoundException, MalformedURLException, FileNotFoundException {
        final Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        final Path path = project.getMcdName() != null
                ? Paths.get(PROJECT_FILE_PATH + "/" + id).resolve("mcd-" + project.getMcdName())
                : null;
        if (path == null) throw new FileNotFoundException(id, "MCD");
        final Resource file = new UrlResource(path.toUri());
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + project.getMcdName() + "\"");
        return ResponseEntity.ok().headers(headers).body(file);
    }

    @PostMapping("/projects/{id}/mcd")
    public @ResponseBody
    ResponseEntity<Project> uploadMcd(Principal principal, @PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException, ProjectNotFoundException {
        final Path dest = Path.of(PROJECT_FILE_PATH + "/" + id + "/mcd-" + file.getOriginalFilename());
        Files.createDirectories(dest.getParent());
        final Project project = projectService.findProjectByIdForUser(principal, id);
        file.transferTo(dest.toAbsolutePath());
        project.setMcdName(file.getOriginalFilename());
        return ResponseEntity.ok(projectRepository.save(project));
    }

    @GetMapping("/projects/{id}/mfc")
    public ResponseEntity<Resource> serveMfcFile(@PathVariable long id) throws ProjectNotFoundException, MalformedURLException, FileNotFoundException {
        final Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        final Path path = project.getMfcName() != null
                ? Paths.get(PROJECT_FILE_PATH + "/" + id).resolve("mfc-" + project.getMfcName())
                : null;
        if (path == null) throw new FileNotFoundException(id, "MFC");
        final Resource file = new UrlResource(path.toUri());
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + project.getMfcName() + "\"");
        return ResponseEntity.ok().headers(headers).body(file);
    }

    @PostMapping("/projects/{id}/mfc")
    public @ResponseBody
    ResponseEntity<Project> uploadMfc(Principal principal, @PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException, ProjectNotFoundException {
        final Path dest = Path.of(PROJECT_FILE_PATH + "/" + id + "/mfc-" + file.getOriginalFilename());
        Files.createDirectories(dest.getParent());
        final Project project = projectService.findProjectByIdForUser(principal, id);
        file.transferTo(dest.toAbsolutePath());
        project.setMfcName(file.getOriginalFilename());
        return ResponseEntity.ok(projectRepository.save(project));
    }
}
