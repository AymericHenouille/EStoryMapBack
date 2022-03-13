package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.Workspace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {

    Workspace findByIdw(long idw);

    @Query(value = "SELECT * FROM workspaces WHERE ?1 = owner_id ", nativeQuery = true)
    Iterable<Workspace> findAllByUser(long id);

    @Query(value = "SELECT (idw, label, color, emoticon, owner_id) FROM workspaces" +
            "INNER JOIN shared_workspaces ON workspaces.idw = shared_workspaces.workspace_id" +
            "WHERE shared_workspaces.user_id = ?1", nativeQuery = true)
    Iterable<Workspace> findAllSharedWithId(long id);
}
