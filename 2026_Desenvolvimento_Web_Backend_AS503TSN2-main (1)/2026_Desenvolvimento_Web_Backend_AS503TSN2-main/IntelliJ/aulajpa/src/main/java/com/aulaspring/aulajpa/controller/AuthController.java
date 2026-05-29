package com.aulaspring.aulajpa.controller;

import com.aulaspring.aulajpa.dto.AutenticacaoDTO;
import com.aulaspring.aulajpa.dto.TokenDTO;
import com.aulaspring.aulajpa.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public TokenDTO autenticar(@RequestBody AutenticacaoDTO autenticacao) {
        return usuarioService.autenticar(autenticacao);
    }
}