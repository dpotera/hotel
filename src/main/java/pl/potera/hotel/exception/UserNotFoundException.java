package pl.potera.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with given id not found in database")
public class UserNotFoundException extends RuntimeException {
}
