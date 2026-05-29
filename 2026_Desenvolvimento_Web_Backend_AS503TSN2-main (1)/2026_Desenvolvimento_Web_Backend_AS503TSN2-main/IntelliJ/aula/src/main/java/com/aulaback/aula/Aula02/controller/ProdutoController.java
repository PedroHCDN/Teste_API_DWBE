package com.aulaback.aula.Aula02.controller;

import com.aulaback.aula.Aula02.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aula02/produto")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();

    public ProdutoController()
    {
        produtos.add(new Produto(1, "Caderno", "10 matérias", 10d));
        produtos.add(new Produto(2, "caneta", "tipo bic", 2.5));
        produtos.add(new Produto(3, "Notebook", "HP", 5000d));
    }
    @GetMapping
    public List<Produto> listar(){
        return produtos;
    }
    @GetMapping("{id}")
    public Produto porId(@PathVariable int id){
        for (Produto p : produtos){
            if(p.getId() == id)
                return p;
        }
        return null;
    }

    @PostMapping
    public Produto gravarProduto(@RequestBody Produto produto){
        produtos.add(produto);
        return produto;
    }

    @DeleteMapping("{id}")
    public String excluiProduto(@PathVariable int id){
        for(Produto p : produtos){
            if(p.getId() == id)
            {
                produtos.remove(p);
                return "Excluído com sucesso!";
            }
        }
        return "Produto não encontrado!";
    }

    @PutMapping("{id}")
    public Produto atualizaProduto(@PathVariable int id, @RequestBody Produto produto){
        for (Produto p : produtos){
            if(p.getId() == id){
                p.setNome(produto.getNome());
                p.setDescricao(produto.getDescricao());
                p.setPreco(produto.getPreco());
                return p;
            }
        }
        return null;
    }

}
