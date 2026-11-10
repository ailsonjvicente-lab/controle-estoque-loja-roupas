package com.primeiro_projeto.loja_roupas.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Balanceamento {

    @Id
    @GeneratedValue

    private Long id;


    @Enumerated(EnumType.STRING)
    private Periodicidade periodicidade;

    private LocalDateTime dataUltimoBalanceamento;

    public Balanceamento() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Periodicidade periodicidade) {
        this.periodicidade = periodicidade;
    }

    public LocalDateTime getDataUltimoBalanciamento() {
        return dataUltimoBalanceamento;
    }

    public void setDataUltimoBalanciamento(LocalDateTime dataUltimoBalanciamento) {
        this.dataUltimoBalanceamento = dataUltimoBalanciamento;
    }

    public boolean isAtrasado() {
        if (dataUltimoBalanceamento == null) {
            return true;

        }

        LocalDateTime limite = (periodicidade == Periodicidade.DIARIO) ? dataUltimoBalanceamento.plusDays(1) : dataUltimoBalanceamento.plusWeeks(1);
        return LocalDateTime.now().isAfter(limite);
    }

}
