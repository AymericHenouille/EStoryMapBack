package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.UserSensitive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSensitiveRepository extends CrudRepository<UserSensitive, Long> {
    Optional<UserSensitive> findByUserId(long id);
}
