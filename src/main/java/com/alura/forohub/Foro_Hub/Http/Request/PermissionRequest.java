package com.alura.forohub.Foro_Hub.Http.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionRequest {

    @NotBlank
    private String name;

}
