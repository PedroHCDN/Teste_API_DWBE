package com.aulaspring.aulajpa.controller;

import com.aulaspring.aulajpa.model.Aluno;
import com.aulaspring.aulajpa.model.Disciplina;
import com.aulaspring.aulajpa.model.Endereco;
import com.aulaspring.aulajpa.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bean")
public class BeanController {

    @Autowired
    private Aluno alunoPadrao;

    @Autowired
    private Endereco endPadrao;

    @Autowired
    @Qualifier("Back")
    private Disciplina disciplinaWeb;

    @Autowired
    @Qualifier("Poo")
    private Disciplina disciplinaPoo;

    @Autowired
    private Professor professorPadrao;

    @GetMapping
    public Aluno getAluno()
    {
        alunoPadrao.setEndereco(endPadrao);
        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinaWeb.setProfessor(professorPadrao);
        disciplinas.add(disciplinaWeb);
        disciplinas.add(disciplinaPoo);
        alunoPadrao.setDisciplinas(disciplinas);
        return alunoPadrao;
    }
}
