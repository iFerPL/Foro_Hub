package com.alura.forohub.Foro_Hub;

import com.alura.forohub.Foro_Hub.Modelos.Permission;
import com.alura.forohub.Foro_Hub.Modelos.Role;
import com.alura.forohub.Foro_Hub.Modelos.Usuario;
import com.alura.forohub.Foro_Hub.Repositorios.IUsuarioRepo;
import com.alura.forohub.Foro_Hub.Repositorios.IPermissionRepo;
import com.alura.forohub.Foro_Hub.Repositorios.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ForoHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

	@Autowired
	private IUsuarioRepo IUsuarioRepo;

	@Autowired
	private IRoleRepo IRoleRepo;

	@Autowired
	private IPermissionRepo IPermissionRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init(){
		return args -> {
			System.out.println("Cargando datos por favor espere...");

			List.of(
					"Crear_role","Update_role","Borrar_role","Leer_role",
					"Crear_permission","Update_permission","Borrar_permission","Read_permission",
					"Crear_topico","Update_topico","Borrar_topico","Leer_topico","Todos_topic"
			).forEach(permission -> {
				IPermissionRepo.save(Permission.builder()
						.name(permission)
						.build());
			});

			List<Permission> permissions = List.of(
					"Crear_topico","Update_topico","Borrar_topico","Leer_topico"
			).stream().map(permission ->
					IPermissionRepo.findByName(permission)
			).toList();

			Role user = IRoleRepo.save(Role.builder()
					.name("USUARIO")
					.permissions(permissions)
					.build());

			Role root = IRoleRepo.save(Role.builder()
					.name("ROOT")
					.permissions(IPermissionRepo.findAll())
					.build());

			IUsuarioRepo.save(Usuario.builder()
					.username("admin")
					.password(passwordEncoder.encode("admin"))
					.role(root)
					.enable(true)
					.build());

			IUsuarioRepo.save(Usuario.builder()
					.username("usuario")
					.password(passwordEncoder.encode("usuario"))
					.role(user)
					.enable(true)
					.build());
		};
	}

}
