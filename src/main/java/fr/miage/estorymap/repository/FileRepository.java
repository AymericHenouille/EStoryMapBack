package fr.miage.estorymap.repository;

import fr.miage.estorymap.entity.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
