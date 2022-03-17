package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.Workspace;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {

    Optional<Workspace> findById(long id);
    Iterable<Workspace> findAll();

}
