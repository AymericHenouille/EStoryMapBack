package fr.miage.estorymap.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends Exception {

    public FileNotFoundException(long id, String type) {
        super("Project with id " + id + " doesn't contain a " + type + " file");
    }
}
