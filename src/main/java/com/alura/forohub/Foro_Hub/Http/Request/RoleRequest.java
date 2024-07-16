package com.alura.forohub.Foro_Hub.Http.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private List<PermissionRequest> permissions;

}
