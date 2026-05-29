package com.aulaback.aula.Aula03.controller;

import com.aulaback.aula.Aula03.model.Usuario;
import com.aulaback.aula.Aula03.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula03/usuario")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }
    @GetMapping
    public List<Usuario> todos(){
        return usuarioRepository.findAll();
    }
    @GetMapping("{id}")
    public Usuario porId(@PathVariable Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    @DeleteMapping("{id}")
    public String exclui(@PathVariable Long id){
        usuarioRepository.deleteById(id);
        return "Excluído com sucesso.";
    }
    @PutMapping("{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioAtualizar = usuarioRepository.findById(id).orElse(null);
        if(usuarioAtualizar == null)
            return usuarioAtualizar;
        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setEmail(usuario.getEmail());
        usuarioAtualizar.setSenha(usuario.getSenha());
        usuarioRepository.save(usuarioAtualizar);
        return usuarioAtualizar;
    }
    @GetMapping("/porNome")
    public List<Usuario> porNome(@RequestParam String nome){
        return usuarioRepository.findByNome(nome);
    }
}
