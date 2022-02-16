package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
