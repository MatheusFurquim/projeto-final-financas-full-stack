package br.com.matheus.financas.web.controller;

import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosAutenticacao;
import br.com.matheus.financas.domain.model.dto.DadosCadastroUsuario;
import br.com.matheus.financas.domain.model.dto.DadosToken;
import br.com.matheus.financas.domain.service.UsuarioService;
import br.com.matheus.financas.infra.spring.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<DadosToken> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }

    @PostMapping("cadastrar")
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        this.service.cadastrar(dados);
        return ResponseEntity.ok().build();
    }

}
