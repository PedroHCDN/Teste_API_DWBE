package com.aulaspring.aulajpa.configs;

import com.aulaspring.aulajpa.model.Aluno;
import com.aulaspring.aulajpa.model.Disciplina;
import com.aulaspring.aulajpa.model.Endereco;
import com.aulaspring.aulajpa.model.Professor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EscolaConfiguration {

    @Bean
    public Aluno getAlunoPadrao()
    {
        Aluno a = new Aluno();
        a.setRa("123456");
        a.setNome("Edson Padrão");
        a.setEmail("edson.feitosa@facens.br");
        return a;
    }
    @Bean
    public Endereco getEnderecoPadrao()
    {
        Endereco e = new Endereco();
        e.setRua("Rua padrão");
        e.setBairro("Jd. Teste");
        e.setNumero(1);
        e.setCidade("Sorocaba");
        e.setEstado("SP");
        e.setPais("Brasil");
        return e;
    }
    @Bean(name = "Back")
    public Disciplina getWebBack()
    {
        Disciplina d = new Disciplina();
        d.setId(1l);
        d.setNome("Programação Web Backend");
        return d;
    }
    @Bean(name = "Poo")
    public Disciplina getPoo(){
        Disciplina d = new Disciplina();
        d.setId(2l);
        d.setNome("Programação Orientada a Objetos");
        return d;
    }

    @Bean
    @Primary
    public Professor getProf1(){
        Professor p = new Professor();
        p.setId(1l);
        p.setNome("Edson Feitosa");
        p.setEmail("edson.feitosa@facens.br");
        return p;
    }
    @Bean
    public Professor getprof2(){
        Professor p = new Professor();
        p.setId(2l);
        p.setNome("Rafael Moreno");
        p.setEmail("rafael.moreno@facens.br");
        return p;
    }
}
