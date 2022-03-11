package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
