package com.primeiro_projeto.loja_roupas.repository;

import com.primeiro_projeto.loja_roupas.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
