package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.service.ProjectService;
import fr.miage.estorymap.service.Verificator;
import fr.miage.estorymap.utils.exception.FileNotFoundException;
import fr.miage.estorymap.utils.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class VerificatorController {

    @Autowired
    private Verificator verificator;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/{id}/verify")
    public ResponseEntity<String> verify(@PathVariable long id, Principal principal) throws ProjectNotFoundException, FileNotFoundException {
        System.out.println("Verify");
        final Project project = projectService.findProjectByIdForUser(principal, id);

        if (project.getBpmnName() == null) throw new FileNotFoundException(id, "BPMN");
        if (project.getMfcName() == null) throw new FileNotFoundException(id, "MFC");
        if (project.getMcdName() == null) throw new FileNotFoundException(id, "MCD");

        final String bpmnPath = "projects/" + id + "/bpmn-" + project.getBpmnName();
        final String mfcPath = "projects/" + id + "/mfc-" + project.getMfcName();
        final String mcdPath = "projects/" + id + "/mcd-" + project.getMcdName();

        return ResponseEntity.ok(verificator.verify(bpmnPath, mfcPath, mcdPath));
    }
}
