package com.alura.forohub.Foro_Hub.Controles;


import com.alura.forohub.Foro_Hub.Excepciones.RoleException;
import com.alura.forohub.Foro_Hub.Http.Request.RoleRequest;
import com.alura.forohub.Foro_Hub.Servicios.RoleServ;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class ControlRole {

    @Autowired
    private RoleServ roleServ;

    @PreAuthorize("hasAuthority('Create_role')")
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleRequest roleRequest){
        return ResponseEntity.ok(roleServ.createRole(roleRequest));
    }

    @PreAuthorize("hasAuthority('Update_role')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable String id, @RequestBody @Valid RoleRequest roleRequest) throws RoleException {
        return ResponseEntity.ok(roleServ.updateRole(id, roleRequest));
    }

    @PreAuthorize("hasAuthority('Delete_role')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable String id) throws RoleException {
        return ResponseEntity.ok(roleServ.deleteRole(id));
    }

    @PreAuthorize("hasAuthority('Read_role')")
    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleServ.getAllRoles());
    }

    @PreAuthorize("hasAuthority('Read_role')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable String id) throws RoleException {
        return ResponseEntity.ok(roleServ.getRoleByID(id));
    }

}
