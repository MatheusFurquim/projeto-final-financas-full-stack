package br.com.matheus.financas.domain.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank(message = "Email é obrigatório!")
        @Email(message = "Email no formato inválido!")
        String email,
        @NotBlank(message = "Senha é obrigatória!")
        String senha
) {
}
