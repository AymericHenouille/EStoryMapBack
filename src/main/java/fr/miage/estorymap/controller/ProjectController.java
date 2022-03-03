package fr.miage.estorymap.controller;

import fr.miage.estorymap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

}
