package com.primeiro_projeto.loja_roupas.controller;

import com.primeiro_projeto.loja_roupas.Model.Balanceamento;
import com.primeiro_projeto.loja_roupas.Model.Periodicidade;
import com.primeiro_projeto.loja_roupas.repository.BalanceamentoRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/balanceamento")

public class BalanceamentoController {


    private final BalanceamentoRepository balanceamentoRepository;

    public BalanceamentoController(BalanceamentoRepository balanceamentoRepository) {
        this.balanceamentoRepository = balanceamentoRepository;
    }


    private Balanceamento buscarOuCriar(){
        List<Balanceamento> lista = balanceamentoRepository.findAll();

        if (lista.isEmpty()) {
            Balanceamento novo = new Balanceamento();
            novo.setPeriodicidade(Periodicidade.SEMANAL);
            return balanceamentoRepository.save(novo);

        }

        return lista.get(0);
    }

    @GetMapping
    public Balanceamento ver(){
        return buscarOuCriar();
    }

    @PutMapping("/periodicidade")
    public Balanceamento mudarPeriodicidade(@RequestBody Balanceamento dados) {
        Balanceamento b = buscarOuCriar();
        b.setPeriodicidade(dados.getPeriodicidade());
        return balanceamentoRepository.save(b);

    }

    @PostMapping("/realizar")
    public Balanceamento realizar() {
        Balanceamento b = buscarOuCriar();
        b.setDataUltimoBalanciamento(LocalDateTime.now());
        return balanceamentoRepository.save(b);
    }



}
