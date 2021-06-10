package platform;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no found code")
public class CodeNotFoundException extends RuntimeException{
    public CodeNotFoundException(String message) {
        super(message);
    }
}
