package br.com.matheus.financas.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questionarios_perfil_risco")
public class QuestionarioPerfilRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    private Character pergunta1;
    private Character pergunta2;
    private Character pergunta3;
    private Character pergunta4;
    private Character pergunta5;
    private Character pergunta6;
    private Character pergunta7;

    //Construtor default exigido pela JPA
    public QuestionarioPerfilRisco() {
    }

    public QuestionarioPerfilRisco(Usuario usuario, Character pergunta1, Character pergunta2, Character pergunta3, Character pergunta4, Character pergunta5, Character pergunta6, Character pergunta7) {
        this.usuario = usuario;
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
        this.pergunta3 = pergunta3;
        this.pergunta4 = pergunta4;
        this.pergunta5 = pergunta5;
        this.pergunta6 = pergunta6;
        this.pergunta7 = pergunta7;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Character getPergunta1() {
        return pergunta1;
    }

    public Character getPergunta2() {
        return pergunta2;
    }

    public Character getPergunta3() {
        return pergunta3;
    }

    public Character getPergunta4() {
        return pergunta4;
    }

    public Character getPergunta5() {
        return pergunta5;
    }

    public Character getPergunta6() {
        return pergunta6;
    }

    public Character getPergunta7() {
        return pergunta7;
    }

}
