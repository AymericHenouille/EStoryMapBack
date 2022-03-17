package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.Project;
import org.springframework.data.repository.CrudRepository;

import java.security.Principal;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
