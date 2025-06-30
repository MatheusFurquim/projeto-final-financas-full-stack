package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.dto.DadosCadastroUsuario;
import br.com.matheus.financas.domain.model.dto.DadosUsuario;
import br.com.matheus.financas.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        var usuario = this.service.cadastrar(dados);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<DadosUsuario>> listar(Pageable paginacao) {
        var usuarios = this.service.listar(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosUsuario> buscarPorId(@PathVariable Long id) {
        var usuario = this.service.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DadosUsuario> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroUsuario dados) {
        var usuario = this.service.atualizar(id, dados);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
