package com.alura.forohub.Foro_Hub.Repositorios;


import com.alura.forohub.Foro_Hub.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);

}
