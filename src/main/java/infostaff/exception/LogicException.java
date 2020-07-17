package infostaff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by https://github.com/kwanpham
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LogicException extends Exception {

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
