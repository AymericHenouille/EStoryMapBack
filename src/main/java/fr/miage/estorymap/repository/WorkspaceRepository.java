package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.Workspace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {

    @Query(value = "SELECT * FROM workspace WHERE idw IN ?1", nativeQuery = true)
    Iterable<Workspace> findWorkspacesByIds(Iterable<Long> idList);
}
