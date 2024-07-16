package com.alura.forohub.Foro_Hub.Servicios;




import com.alura.forohub.Foro_Hub.Excepciones.PermissionException;
import com.alura.forohub.Foro_Hub.Http.Request.PermissionRequest;
import com.alura.forohub.Foro_Hub.Http.Response.PermissionResponse;
import com.alura.forohub.Foro_Hub.Modelos.Permission;
import com.alura.forohub.Foro_Hub.Utils.PermissionUt;
import com.alura.forohub.Foro_Hub.Repositorios.IPermissionRepo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServ {

    @Autowired
    private IPermissionRepo permissionRepository;

    @Transactional
    public PermissionResponse savePermission(PermissionRequest permissionRequest) {

        Permission permission = permissionRepository.save(PermissionUt.toPermission(permissionRequest));

        return PermissionUt.toPermissionResponse(permission);
    }

    public PermissionResponse getPermission(String id) throws PermissionException {

        Permission permission = permissionRepository.findById(id).orElse(null);

        if (permission == null) {
            throw new PermissionException("Permission not found");
        }

        return PermissionUt.toPermissionResponse(permission);
    }

    public List<Permission> getAllPermissions() {

        return permissionRepository.findByEnable(true);
    }

    @Transactional
    public void deletePermission(String id) throws PermissionException {

        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new PermissionException("Permission not found"));

        permission.setEnable(false);
    }

    @Transactional
    public PermissionResponse updatePermission(String id, PermissionRequest permissionRequest) {

        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));

        permission.setName(permissionRequest.getName());

        return PermissionUt.toPermissionResponse(permission);
    }
}