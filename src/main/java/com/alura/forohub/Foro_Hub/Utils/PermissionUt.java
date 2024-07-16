package com.alura.forohub.Foro_Hub.Utils;

import com.alura.forohub.Foro_Hub.Http.Request.PermissionRequest;
import com.alura.forohub.Foro_Hub.Http.Response.PermissionResponse;
import com.alura.forohub.Foro_Hub.Modelos.Permission;

public class PermissionUt {
    public static PermissionResponse toPermissionResponse(Permission permission){
        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

    public static Permission toPermission(PermissionRequest permissionRequest){
        return Permission.builder()
                .name(permissionRequest.getName())
                .build();
    }
}
