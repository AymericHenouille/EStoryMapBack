package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.Shared;
import fr.miage.estorymap.entity.id.SharedId;
import org.springframework.data.repository.CrudRepository;

public interface SharedRepository extends CrudRepository<Shared, SharedId> {
}
