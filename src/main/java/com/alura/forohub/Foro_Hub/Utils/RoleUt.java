package com.alura.forohub.Foro_Hub.Utils;



import com.alura.forohub.Foro_Hub.Http.Request.RoleRequest;
import com.alura.forohub.Foro_Hub.Http.Response.RoleResponse;
import com.alura.forohub.Foro_Hub.Modelos.Permission;
import com.alura.forohub.Foro_Hub.Modelos.Role;
import com.alura.forohub.Foro_Hub.Repositorios.IPermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoleUt {

    @Autowired
    private static IPermissionRepo IPermissionRepo;

    public static RoleResponse toRoleResponse(Role role){
        return RoleResponse.builder()
                .name(role.getName())
                .permissions(
                        role.getPermissions().stream()
                                .map(PermissionUt::toPermissionResponse)
                                .toList()
                )
                .build();
    }

    public static Role toRole(RoleRequest roleRequest){
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                IPermissionRepo.findByNameAndEnable(permissionRequest.getName(),true).ifPresent(permissions::add)
        );
        return Role.builder()
                .name(roleRequest.getName())
                .permissions(permissions)
                .build();
    }

}

