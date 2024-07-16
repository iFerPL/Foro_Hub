package com.alura.forohub.Foro_Hub.Controles;


import com.alura.forohub.Foro_Hub.Excepciones.PermissionException;
import com.alura.forohub.Foro_Hub.Http.Request.PermissionRequest;
import com.alura.forohub.Foro_Hub.Servicios.PermissionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class ControlDePermisos {

    @Autowired
    private PermissionServ permissionServ;

    @PreAuthorize("hasAuthority('Read_permission')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPermissionById(@PathVariable String id) throws PermissionException {
        return ResponseEntity.ok(permissionServ.getPermission(id));
    }

    @PreAuthorize("hasAuthority('Read_permission')")
    @GetMapping
    public ResponseEntity<?> getAllPermissions() {
        return ResponseEntity.ok(permissionServ.getAllPermissions());
    }

    @PreAuthorize("hasAuthority('Create_permission')")
    @PostMapping
    public ResponseEntity<?> createPermission(@RequestBody PermissionRequest permission) {
        return ResponseEntity.ok(permissionServ.savePermission(permission));
    }

    @PreAuthorize("hasAuthority('Update_permission')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePermission(@PathVariable String id, @RequestBody PermissionRequest permission) {
        return ResponseEntity.ok(permissionServ.updatePermission(id, permission));
    }

    @PreAuthorize("hasAuthority('Delete_permission')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable String id) throws PermissionException {
        permissionServ.deletePermission(id);
        return ResponseEntity.ok().build();
    }

}
