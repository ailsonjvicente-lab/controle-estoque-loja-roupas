package com.primeiro_projeto.loja_roupas.controller;

import com.primeiro_projeto.loja_roupas.Model.Movimentacao;
import com.primeiro_projeto.loja_roupas.Model.Produto;
import com.primeiro_projeto.loja_roupas.Model.TipoMovimentacao;
import com.primeiro_projeto.loja_roupas.repository.MovimentacaoRepository;
import com.primeiro_projeto.loja_roupas.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoController(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Movimentacao> listar() {

        return movimentacaoRepository.findAll();
    }

    @PostMapping
    public Movimentacao registrar(@RequestBody Movimentacao movimentacao) {

        Produto produto = produtoRepository.findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (movimentacao.getTipo() == TipoMovimentacao.VENDA) {
            if (produto.getEstoqueAtual() < movimentacao.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente");
            }

            produto.setEstoqueAtual(produto.getEstoqueAtual() - movimentacao.getQuantidade());

            movimentacao.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(movimentacao.getQuantidade())));

        } else {
            produto.setEstoqueAtual(produto.getEstoqueAtual() + movimentacao.getQuantidade());
        }

        movimentacao.setProduto(produto);
        movimentacao.setData(LocalDateTime.now());
        produtoRepository.save(produto);
        return movimentacaoRepository.save(movimentacao);
    }
}