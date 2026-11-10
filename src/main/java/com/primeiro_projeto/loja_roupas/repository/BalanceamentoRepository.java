package com.primeiro_projeto.loja_roupas.repository;

import com.primeiro_projeto.loja_roupas.Model.Balanceamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceamentoRepository extends JpaRepository<Balanceamento, Long> {
}
