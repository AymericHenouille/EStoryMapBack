package fr.miage.estorymap.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends Exception {

    public ProjectNotFoundException(long id) {
        super("Project with id " + id + " not found");
    }
}
