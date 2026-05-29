package com.aulaspring.aulajpa;

import com.aulaspring.aulajpa.configs.JWTService;
import com.aulaspring.aulajpa.model.Usuario;
import com.aulaspring.aulajpa.repository.UsuarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AulajpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AulajpaApplication.class, args);

		//Testando o JwtService
//		ConfigurableApplicationContext contexto = SpringApplication.run(AulajpaApplication.class);
//		JWTService service = contexto.getBean(JWTService.class);
//		UsuarioRepository usuarioRepository = contexto.getBean(UsuarioRepository.class);
//		PasswordEncoder passwordEncoder = contexto.getBean(PasswordEncoder.class);
//
//		Usuario usuario = new Usuario("Edson", "edson.feitosa@facens.br", passwordEncoder.encode("123"), "123321", "Administrador");
//		String token = service.gerarToken(usuario);
//		System.out.println(token);
//		boolean isValid = service.validarToken(token);
//		System.out.println("Token válido? " + isValid);
//		System.out.println("Usuário: " + service.obterLoginUsuario(token));
//
//		usuarioRepository.save(usuario);

	}

}
