package com.aulaback.aula.Aula01;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula1In")
public class Aula1 {

    @GetMapping("/divisao/{num1}/{num2}")
    public String divisao(@PathVariable double num1, @PathVariable double num2){
        if (num2 == 0) {
            System.out.println("Impossível dividir por zero");
            return "Impossível dividir por zero";
        }
        else{
            System.out.println(Double.toString( num1 / num2));
            return Double.toString( num1 / num2);
        }

    }
    @GetMapping("cumprimento")
    public String cumprimento (@RequestParam("s") String sexo, @RequestParam("n") String nome){
        if ("masculino".equals(sexo))
            return "Olá senhor " + nome;
        else
            return "Olá senhorita " + nome;
    }
}
