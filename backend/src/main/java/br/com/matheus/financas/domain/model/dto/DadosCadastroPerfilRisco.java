package br.com.matheus.financas.domain.model.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPerfilRisco(
        @NotNull(message = "Pergunta 1 é origatória!")
        Character pergunta1,
        @NotNull(message = "Pergunta 2 é origatória!")
        Character pergunta2,
        @NotNull(message = "Pergunta 3 é origatória!")
        Character pergunta3,
        @NotNull(message = "Pergunta 4 é origatória!")
        Character pergunta4,
        @NotNull(message = "Pergunta 5 é origatória!")
        Character pergunta5,
        @NotNull(message = "Pergunta 6 é origatória!")
        Character pergunta6,
        @NotNull(message = "Pergunta 7 é origatória!")
        Character pergunta7) {
}
