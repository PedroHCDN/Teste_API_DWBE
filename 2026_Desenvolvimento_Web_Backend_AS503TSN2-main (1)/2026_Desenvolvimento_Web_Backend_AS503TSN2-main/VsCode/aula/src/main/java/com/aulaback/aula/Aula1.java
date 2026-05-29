package com.aulaback.aula;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/aula1")
public class Aula1 {
    
    @GetMapping
    public String hello() {
        return "Olá VSCode e Spring Boot!";
    }

    @GetMapping("/nome/{nome}")
    public String getNome(@PathVariable String nome) {
        return "Olá " + nome + "!";
    }
}
