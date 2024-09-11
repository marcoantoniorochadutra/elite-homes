package com.elitehomes.core.exception;



import com.elitehomes.core.exception.ref.LoginError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginException extends RuntimeException {

    private LoginError error;

    public LoginException(String message, LoginError error) {
        super(message);
        this.error = error;
    }

}
