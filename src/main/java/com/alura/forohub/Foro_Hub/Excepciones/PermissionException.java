package com.alura.forohub.Foro_Hub.Excepciones;


import lombok.Getter;

@Getter
public class PermissionException extends Exception{

    public PermissionException(String message){
        super(message);
    }

}
