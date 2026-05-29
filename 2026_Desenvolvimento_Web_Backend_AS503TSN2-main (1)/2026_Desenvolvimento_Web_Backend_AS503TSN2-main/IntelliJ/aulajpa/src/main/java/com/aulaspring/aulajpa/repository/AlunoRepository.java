package com.aulaspring.aulajpa.repository;

import com.aulaspring.aulajpa.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
}
