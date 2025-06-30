package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosCadastroDespesa;
import br.com.matheus.financas.domain.model.dto.DadosDespesa;
import br.com.matheus.financas.domain.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("despesas")
public class DespesaController {

    @Autowired
    private DespesaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDespesa> cadastrar(@Valid @RequestBody DadosCadastroDespesa dados, @AuthenticationPrincipal Usuario logado) {
        var despesa = this.service.cadastrar(dados, logado);
        return ResponseEntity.ok(despesa);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDespesa>> listar(Pageable paginacao, @AuthenticationPrincipal Usuario logado) {
        var despesas = this.service.listar(paginacao, logado);
        return ResponseEntity.ok(despesas);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDespesa> buscarPorId(@PathVariable Long id) {
        var despesa = this.service.buscarPorId(id);
        return ResponseEntity.ok(despesa);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DadosDespesa> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroDespesa dados) {
        var despesa = this.service.atualizar(id, dados);
        return ResponseEntity.ok(despesa);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
