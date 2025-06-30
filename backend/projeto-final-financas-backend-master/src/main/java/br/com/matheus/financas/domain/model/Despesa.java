package br.com.matheus.financas.domain.model;

import br.com.matheus.financas.domain.model.dto.DadosCadastroDespesa;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    @ManyToOne
    private Usuario usuario;

    //Construtor default exigido pela JPA
    public Despesa() {}

    public Despesa(DadosCadastroDespesa dados, Usuario usuario) {
        this.preeencherAtributos(dados, usuario);
    }

    public void atualizarDados(DadosCadastroDespesa dados) {
        this.preeencherAtributos(dados, this.usuario);
    }

    private void preeencherAtributos(DadosCadastroDespesa dados, Usuario usuario) {
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.data = dados.data();
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

}
