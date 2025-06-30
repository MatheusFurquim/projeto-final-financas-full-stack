package br.com.matheus.financas.domain.model.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record SaldoMensal(YearMonth mes, BigDecimal totalReceitas, BigDecimal totalDespesas, BigDecimal saldo) {
}
