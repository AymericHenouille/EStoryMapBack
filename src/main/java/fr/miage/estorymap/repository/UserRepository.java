package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "SELECT workspace_id FROM shared_workspaces WHERE user_id = ?1", nativeQuery = true)
    Iterable<Long> findSharedWorkspacesIdByUserId(String id);
}
