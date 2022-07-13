package bianova.bianova.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Role not found")
public class NoRoleFoundException extends RuntimeException {

}
