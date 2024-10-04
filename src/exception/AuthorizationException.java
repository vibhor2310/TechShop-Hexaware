package exception;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

public class AuthorizationException extends AuthenticationException {
    public  AuthorizationException(){
        System.out.println("User Not Found ");
    }

    public String toString(){
        return "incorrect credentials";

    }
}
