package com.amine.ciklumchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GameException extends RuntimeException {

    public GameException() {
        super();
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(Throwable cause) {
        super(cause);
    }
}
