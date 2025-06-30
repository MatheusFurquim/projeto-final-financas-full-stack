package br.com.matheus.financas.domain.model.dto;

import br.com.matheus.financas.domain.model.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDespesa(Long id, String descricao, BigDecimal valor, LocalDate data) {

    public DadosDespesa(Despesa despesa) {
        this(despesa.getId(), despesa.getDescricao(), despesa.getValor(), despesa.getData());
    }

}
