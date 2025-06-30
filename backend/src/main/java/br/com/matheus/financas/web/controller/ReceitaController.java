package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosCadastroReceita;
import br.com.matheus.financas.domain.model.dto.DadosReceita;
import br.com.matheus.financas.domain.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosReceita> cadastrar(@RequestBody @Valid DadosCadastroReceita dados, @AuthenticationPrincipal Usuario logado) {
        var receita = this.service.cadastrar(dados, logado);
        return ResponseEntity.ok(receita);
    }

    @GetMapping
    public ResponseEntity<Page<DadosReceita>> listar(Pageable paginacao, @AuthenticationPrincipal Usuario logado) {
        var receitas = this.service.listar(paginacao, logado);
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosReceita> buscarPorId(@PathVariable Long id) {
        var receita = this.service.buscarPorId(id);
        return ResponseEntity.ok(receita);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DadosReceita> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroReceita dados) {
        var receita = this.service.atualizar(id, dados);
        return ResponseEntity.ok(receita);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
