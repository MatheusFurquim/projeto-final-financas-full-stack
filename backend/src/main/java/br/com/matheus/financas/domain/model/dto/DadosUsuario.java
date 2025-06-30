package br.com.matheus.financas.domain.model.dto;

import br.com.matheus.financas.domain.model.Usuario;

public record DadosUsuario(Long id, String nome, String email, Boolean admin) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.isAdmin());
    }

}
