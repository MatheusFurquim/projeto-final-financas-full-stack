package br.com.matheus.financas.domain.service;

import br.com.matheus.financas.domain.model.Perfil;
import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosAtualizacaoPerfil;
import br.com.matheus.financas.domain.model.dto.DadosCadastroUsuario;
import br.com.matheus.financas.domain.model.dto.DadosUsuario;
import br.com.matheus.financas.domain.repository.PerfilRepository;
import br.com.matheus.financas.domain.repository.UsuarioRepository;
import br.com.matheus.financas.infra.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findByEmail(email);
    }

    public DadosUsuario cadastrar(DadosCadastroUsuario dados) {
        var emailJaCadastrado = this.repository.existsByEmail(dados.email());
        if (emailJaCadastrado) {
            throw new ValidacaoException("Email já cadastrado para outro usuário!");
        }

        var senhaBCrypt = passwordEncoder.encode(dados.senha());
        var perfis = carregarPerfis(dados.admin());

        var usuario = new Usuario(dados, senhaBCrypt, perfis);

        this.repository.save(usuario);
        return new DadosUsuario(usuario);
    }

    public DadosUsuario atualizarPerfil(DadosAtualizacaoPerfil dados, Usuario logado) {
        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            throw new ValidacaoException("Senha e confirmação não batem!");
        }

        var emailJaCadastrado = this.repository.existsByEmailAndIdNot(dados.email(), logado.getId());
        if (emailJaCadastrado) {
            throw new ValidacaoException("Email já cadastrado para outro usuário!");
        }

        var senhaBCrypt = passwordEncoder.encode(dados.senha());
        var usuario = this.repository.findById(logado.getId()).get();
        usuario.atualizarDados(dados, senhaBCrypt);

        this.repository.save(usuario);
        return new DadosUsuario(usuario);
    }

    public Page<DadosUsuario> listar(Pageable paginacao) {
        var usuarios = this.repository.findAll(paginacao);
        return usuarios.map(DadosUsuario::new);
    }

    public DadosUsuario buscarPorId(Long id) {
        var usuario = this.repository.findById(id).get();
        return new DadosUsuario(usuario);
    }

    public DadosUsuario atualizar(Long id, DadosCadastroUsuario dados) {
        var emailJaCadastrado = this.repository.existsByEmailAndIdNot(dados.email(), id);
        if (emailJaCadastrado) {
            throw new ValidacaoException("Email já cadastrado para outro usuário!");
        }

        var usuario = this.repository.findById(id).get();
        var senhaBCrypt = passwordEncoder.encode(dados.senha());
        var perfis = carregarPerfis(dados.admin());

        usuario.atualizarDados(dados, senhaBCrypt, perfis);

        this.repository.save(usuario);
        this.atualizarDadoUsuarioLogado(usuario);
        return new DadosUsuario(usuario);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    private List<Perfil> carregarPerfis(Boolean admin) {
        var perfis = new ArrayList<Perfil>();
        var perfilUser = perfilRepository.findByNome("ROLE_USER");
        perfis.add(perfilUser);

        if (admin != null && admin) {
            var perfilAdmin = perfilRepository.findByNome("ROLE_ADMIN");
            perfis.add(perfilAdmin);
        }

        return perfis;
    }

    public void atualizarDadoUsuarioLogado(Usuario logado) {
        Authentication auth = new PreAuthenticatedAuthenticationToken(logado, null, logado.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
