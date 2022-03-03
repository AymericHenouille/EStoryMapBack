package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "SELECT w FROM workspace w WHERE w.idw = ?1", nativeQuery = true)
    Iterable<Workspace> findAllSharedWorkspaceByUserId(Long id);

    @NamedQuery(name = "findAllSharedWorkspaceByUserId", query = "SELECT w FROM workspace w WHERE w.idw = 1")
}
