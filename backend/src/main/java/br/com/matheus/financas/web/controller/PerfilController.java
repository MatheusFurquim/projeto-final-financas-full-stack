package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosAtualizacaoPerfil;
import br.com.matheus.financas.domain.model.dto.DadosUsuario;
import br.com.matheus.financas.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("perfil")
public class PerfilController {

    @Autowired
    private UsuarioService service;

    @PutMapping
    @Transactional
    public ResponseEntity<DadosUsuario> atualizarDados(@RequestBody @Valid DadosAtualizacaoPerfil dados, @AuthenticationPrincipal Usuario logado) {
        var usuario = this.service.atualizarPerfil(dados, logado);
        return ResponseEntity.ok(usuario);
    }

}
