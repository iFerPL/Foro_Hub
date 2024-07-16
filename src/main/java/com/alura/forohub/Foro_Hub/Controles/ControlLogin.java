package com.alura.forohub.Foro_Hub.Controles;



import com.alura.forohub.Foro_Hub.Http.Request.LoginRequest;
import com.alura.forohub.Foro_Hub.Modelos.Usuario;
import com.alura.forohub.Foro_Hub.Jwt.JwtServicio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class ControlLogin {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtServicio jwtServicio;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        Usuario usuario = (Usuario) userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("This username does not exist");
        }
        Authentication authReq = UsernamePasswordAuthenticationToken
                .authenticated(loginRequest.getUsername(),loginRequest.getPassword(),null);
        this.authenticationManager.authenticate(authReq).getCredentials();
        String token = jwtServicio.generateToken(usuario.getUsername());
        List<String> roles = usuario.getAuthorities().stream().map(a -> a.getAuthority()).toList();
        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "username", usuario.getUsername(),
                        "roles", roles
                )
        );
    }

}
