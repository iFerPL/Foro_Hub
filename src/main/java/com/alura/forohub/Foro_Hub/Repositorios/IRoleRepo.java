package com.alura.forohub.Foro_Hub.Repositorios;


import com.alura.forohub.Foro_Hub.Modelos.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends JpaRepository<Role, String> {
}
