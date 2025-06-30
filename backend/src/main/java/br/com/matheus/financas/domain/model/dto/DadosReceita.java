package br.com.matheus.financas.domain.model.dto;

import br.com.matheus.financas.domain.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosReceita(Long id, String descricao, BigDecimal valor, LocalDate data) {

    public DadosReceita(Receita receita) {
        this(receita.getId(), receita.getDescricao(), receita.getValor(), receita.getData());
    }

}
