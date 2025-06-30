package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.SaldoMensal;
import br.com.matheus.financas.domain.service.RelatorioFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("relatorio-financeiro")
public class RelatorioFinanceiroController {

    @Autowired
    private RelatorioFinanceiroService service;

    @GetMapping
    public ResponseEntity<List<SaldoMensal>> listar(@AuthenticationPrincipal Usuario logado) {
        var relatorio = this.service.gerarRelatorioMensal(logado);
        return ResponseEntity.ok(relatorio);
    }

}
