package br.com.matheus.financas.domain.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank(message = "Nome é origatório!")
        String nome,
        @NotBlank(message = "Email é origatório!")
        @Email(message = "Email em formato inválido!")
        String email,
        @NotBlank(message = "Senha é origatória!")
        String senha,
        Boolean admin) {
}
