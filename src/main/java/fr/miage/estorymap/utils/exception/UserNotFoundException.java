package fr.miage.estorymap.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotFoundException extends Exception {

    public UserNotFoundException(long id) {
        super("User with id " + id + " not found");
    }

    public UserNotFoundException() {
        super("User with not found");
    }
}
