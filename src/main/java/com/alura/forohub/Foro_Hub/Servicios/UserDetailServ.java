package com.alura.forohub.Foro_Hub.Servicios;



import com.alura.forohub.Foro_Hub.Modelos.Usuario;
import com.alura.forohub.Foro_Hub.Repositorios.IUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServ implements UserDetailsService {

    @Autowired
    private IUsuarioRepo IUsuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = IUsuarioRepo.findByUsername(username);
        return user.orElse(null);
    }
}
