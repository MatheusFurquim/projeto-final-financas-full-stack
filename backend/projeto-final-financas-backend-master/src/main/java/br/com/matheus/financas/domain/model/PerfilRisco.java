package br.com.matheus.financas.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perfis_risco")
public class PerfilRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaPerfilRisco categoria;

    @OneToOne
    private Usuario usuario;

    //Construtor default exigido pela JPA
    public PerfilRisco() {
    }

    public PerfilRisco(Usuario usuario, CategoriaPerfilRisco categoria) {
        this.usuario = usuario;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public CategoriaPerfilRisco getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
