package pl.potera.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No rooms available")
public class NoRoomsAvailableException extends RuntimeException {
}
