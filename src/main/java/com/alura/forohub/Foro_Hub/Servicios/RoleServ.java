package com.alura.forohub.Foro_Hub.Servicios;



import com.alura.forohub.Foro_Hub.Excepciones.RoleException;
import com.alura.forohub.Foro_Hub.Http.Request.RoleRequest;
import com.alura.forohub.Foro_Hub.Http.Response.RoleResponse;
import com.alura.forohub.Foro_Hub.Modelos.Permission;
import com.alura.forohub.Foro_Hub.Modelos.Role;
import com.alura.forohub.Foro_Hub.Repositorios.IRoleRepo;
import com.alura.forohub.Foro_Hub.Repositorios.IPermissionRepo;
import com.alura.forohub.Foro_Hub.Utils.RoleUt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServ {

    @Autowired
    private IRoleRepo IRoleRepo;

    @Autowired
    private IPermissionRepo IPermissionRepo;

    @Transactional
    public RoleResponse createRole(RoleRequest roleRequest){
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                IPermissionRepo.findByNameAndEnable(permissionRequest.getName(),true).ifPresent(permissions::add)
        );
        Role role = IRoleRepo.save(Role.builder()
                .name(roleRequest.getName())
                .permissions(permissions)
                .build());
        return RoleUt.toRoleResponse(role);
    }

    @Transactional
    public RoleResponse updateRole(String id, RoleRequest roleRequest) throws RoleException {
        Role role = IRoleRepo.findById(id).orElseThrow(
                () -> new RoleException("Role not found")
        );
        if(!role.isEnable())
            throw new RoleException("Role is not enable");
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                IPermissionRepo.findByNameAndEnable(permissionRequest.getName(),true).ifPresent(permissions::add)
        );
        role.setName(roleRequest.getName());
        role.setPermissions(permissions);
        IRoleRepo.save(role);
        return RoleUt.toRoleResponse(role);
    }

    @Transactional
    public RoleResponse deleteRole(String id) throws RoleException {
        Role role = IRoleRepo.findById(id).orElseThrow(
                () -> new RoleException("Role not found")
        );
        if(!role.isEnable())
            throw new RoleException("Role is not enable");
        role.setEnable(false);
        IRoleRepo.save(role);
        return RoleUt.toRoleResponse(role);
    }

    public RoleResponse getRoleByID(String id) throws RoleException {
        Role role = IRoleRepo.findById(id).orElseThrow(
                () -> new RoleException("Role not found")
        );
        if(!role.isEnable())
            throw new RoleException("Role is not enable");
        return RoleUt.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles() {
        List<Role> roles = IRoleRepo.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        roles.forEach(role -> {
            if(role.isEnable())
                roleResponses.add(RoleUt.toRoleResponse(role));
        });
        return roleResponses;
    }


}
