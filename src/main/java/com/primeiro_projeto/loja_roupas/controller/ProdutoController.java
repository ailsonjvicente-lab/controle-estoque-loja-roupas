package com.primeiro_projeto.loja_roupas.controller;

import com.primeiro_projeto.loja_roupas.Model.Produto;
import com.primeiro_projeto.loja_roupas.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    @GetMapping
    public List<Produto> listar(){

    return produtoRepository.findAll();

    }
    @PostMapping
    public Produto criar(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }
}

