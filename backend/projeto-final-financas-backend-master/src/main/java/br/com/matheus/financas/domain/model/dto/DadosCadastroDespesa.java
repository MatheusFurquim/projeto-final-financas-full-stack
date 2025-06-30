package br.com.matheus.financas.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroDespesa(
        @NotBlank(message = "Descrição é origatória!")
        String descricao,
        @NotNull(message = "Valor é origatório!")
        BigDecimal valor,
        @NotNull(message = "Data é origatória!")
        LocalDate data) {
}
