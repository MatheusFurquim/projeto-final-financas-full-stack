package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosCadastroPerfilRisco;
import br.com.matheus.financas.domain.model.dto.DadosPerfilRisco;
import br.com.matheus.financas.domain.service.PerfilRiscoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("perfil-risco")
public class PerfilRiscoController {

    @Autowired
    private PerfilRiscoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosPerfilRisco> cadastrarPerfilRisco(@Valid @RequestBody DadosCadastroPerfilRisco dados, @AuthenticationPrincipal Usuario logado) {
        var dadosPerfilRisco = service.cadastrarPerfilRisco(dados, logado);
        return ResponseEntity.ok(dadosPerfilRisco);
    }

    @PostMapping("refazer")
    @Transactional
    public ResponseEntity<Void> resetarQuestionario(@AuthenticationPrincipal Usuario logado) {
        service.resetarQuestionario(logado);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<DadosPerfilRisco> carregarQuestionarioPerfilRisco(@AuthenticationPrincipal Usuario logado) {
        var perfil = service.carregarPerfilRisco(logado);
        var questionarioPerfilRisco = service.carregarQuestionarioPerfilRisco(logado);
        if (perfil != null && questionarioPerfilRisco != null) {
            var dadosPerfilRisco = new DadosPerfilRisco(perfil, questionarioPerfilRisco);
            return ResponseEntity.ok(dadosPerfilRisco);
        }

        return ResponseEntity.ok().build();
    }

}
