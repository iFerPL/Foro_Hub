package com.alura.forohub.Foro_Hub.Handlers;

import com.alura.forohub.Foro_Hub.Excepciones.PermissionException;
import com.alura.forohub.Foro_Hub.Excepciones.RoleException;
import com.alura.forohub.Foro_Hub.Excepciones.TopicException;
import com.alura.forohub.Foro_Hub.Excepciones.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ModelsHandlers {

    @ExceptionHandler(PermissionException.class)
    public String handlePermissionException(PermissionException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(TopicException.class)
    public String handleTopicException(TopicException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UserException.class)
    public String handleUserException(UserException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(RoleException.class)
    public String handleRoleException(RoleException exception) {
        return exception.getMessage();
    }

}
