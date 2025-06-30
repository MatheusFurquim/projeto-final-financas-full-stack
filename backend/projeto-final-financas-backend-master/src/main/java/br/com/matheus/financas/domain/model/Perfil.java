package br.com.matheus.financas.domain.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfis")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public boolean isAdmin() {
        return this.nome.equals("ROLE_ADMIN");
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return nome;
    }

}
