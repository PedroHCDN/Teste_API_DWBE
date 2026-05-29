package com.aulaback.aula.Aula03.repository;

import com.aulaback.aula.Aula03.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findByNome (String nome);

    //faltou implementar os endpoints no controller
    public List<Usuario> findByEmailAndSenha(String email, String senha);
    public List<Usuario> findByEmailContaining(String email);
    public List<Usuario> findByEmailStartingWith(String email);
}
