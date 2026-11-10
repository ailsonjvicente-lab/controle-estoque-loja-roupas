package com.primeiro_projeto.loja_roupas.repository;

import com.primeiro_projeto.loja_roupas.Model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
