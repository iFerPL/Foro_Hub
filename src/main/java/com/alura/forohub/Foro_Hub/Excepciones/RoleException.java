package com.alura.forohub.Foro_Hub.Excepciones;


import lombok.Getter;

@Getter
public class RoleException extends Exception{

    public RoleException(String message) {
        super(message);
    }
}
