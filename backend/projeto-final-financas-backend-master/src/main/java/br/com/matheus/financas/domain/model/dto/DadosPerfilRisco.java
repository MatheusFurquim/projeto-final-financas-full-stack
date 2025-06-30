package br.com.matheus.financas.domain.model.dto;

import br.com.matheus.financas.domain.model.CategoriaPerfilRisco;
import br.com.matheus.financas.domain.model.PerfilRisco;
import br.com.matheus.financas.domain.model.QuestionarioPerfilRisco;

public record DadosPerfilRisco(CategoriaPerfilRisco categoria, Character pergunta1, Character pergunta2, Character pergunta3, Character pergunta4, Character pergunta5, Character pergunta6, Character pergunta7) {

    public DadosPerfilRisco(PerfilRisco perfilRisco, QuestionarioPerfilRisco questionarioPerfilRisco) {
        this(
                perfilRisco.getCategoria(),
                questionarioPerfilRisco.getPergunta1(),
                questionarioPerfilRisco.getPergunta2(),
                questionarioPerfilRisco.getPergunta3(),
                questionarioPerfilRisco.getPergunta4(),
                questionarioPerfilRisco.getPergunta5(),
                questionarioPerfilRisco.getPergunta6(),
                questionarioPerfilRisco.getPergunta7());
    }

}
