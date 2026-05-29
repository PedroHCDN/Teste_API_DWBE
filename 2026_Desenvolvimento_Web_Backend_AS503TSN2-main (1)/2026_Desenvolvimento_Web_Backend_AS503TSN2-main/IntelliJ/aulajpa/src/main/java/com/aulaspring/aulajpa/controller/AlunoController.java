package com.aulaspring.aulajpa.controller;

import com.aulaspring.aulajpa.model.Aluno;
import com.aulaspring.aulajpa.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService){

        this.alunoService = alunoService;
    }

    @PostMapping
    public Aluno salvarAluno(@RequestBody Aluno aluno){
        return this.alunoService.salvar(aluno);
    }
    @GetMapping
    public List<Aluno> todosAlunos(){
        return this.alunoService.todos();
    }
    @GetMapping("{ra}")
    public Aluno alunoPorRa(@PathVariable String ra){
        return this.alunoService.porRA(ra);
    }
    @PutMapping("{ra}")
    public Aluno atualizaAluno(@PathVariable String ra, @RequestBody Aluno aluno){
        return this.alunoService.atualizar(ra, aluno);
    }
    @DeleteMapping("{ra}")
    public boolean excluirAluno(@PathVariable String ra){
        return this.alunoService.excluir(ra);
    }
}
