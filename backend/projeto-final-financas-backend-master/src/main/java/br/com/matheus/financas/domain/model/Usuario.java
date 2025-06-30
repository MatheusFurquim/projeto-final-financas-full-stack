package br.com.matheus.financas.domain.model;

import br.com.matheus.financas.domain.model.dto.DadosAtualizacaoPerfil;
import br.com.matheus.financas.domain.model.dto.DadosCadastroUsuario;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis = new ArrayList<>();

    //Construtor default exigido pela JPA
    public Usuario() {
    }

    public Usuario(DadosCadastroUsuario dados, String senha, List<Perfil> perfis) {
        this.preeencherAtributos(dados, senha, perfis);
    }

    public void atualizarDados(DadosCadastroUsuario dados, String senha, List<Perfil> perfis) {
        this.preeencherAtributos(dados, senha, perfis);
    }

    public void atualizarDados(DadosAtualizacaoPerfil dados, String senha) {
        this.preeencherAtributos(dados, senha);
    }

    private void preeencherAtributos(DadosCadastroUsuario dados, String senha, List<Perfil> perfis) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = senha;
        this.perfis = perfis;
    }

    private void preeencherAtributos(DadosAtualizacaoPerfil dados, String senha) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = senha;
    }

    public Boolean isAdmin() {
        return this.perfis.stream().anyMatch(Perfil::isAdmin);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

