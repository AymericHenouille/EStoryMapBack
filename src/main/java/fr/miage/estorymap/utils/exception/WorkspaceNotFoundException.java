package fr.miage.estorymap.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkspaceNotFoundException extends Exception {

    public WorkspaceNotFoundException(long id) {
        super("Workspace with id: " + id + " not found.");
    }

}
