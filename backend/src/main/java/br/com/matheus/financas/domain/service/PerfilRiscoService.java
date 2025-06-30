package br.com.matheus.financas.domain.service;

import br.com.matheus.financas.domain.model.CategoriaPerfilRisco;
import br.com.matheus.financas.domain.model.PerfilRisco;
import br.com.matheus.financas.domain.model.QuestionarioPerfilRisco;
import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosCadastroPerfilRisco;
import br.com.matheus.financas.domain.model.dto.DadosPerfilRisco;
import br.com.matheus.financas.domain.repository.PerfilRiscoRepository;
import br.com.matheus.financas.domain.repository.QuestionarioPerfilRiscoRepository;
import br.com.matheus.financas.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilRiscoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRiscoRepository perfilRiscoRepository;

    @Autowired
    private QuestionarioPerfilRiscoRepository questionarioPerfilRiscoRepository;

    public DadosPerfilRisco cadastrarPerfilRisco(DadosCadastroPerfilRisco dados, Usuario usuario) {
        usuario = usuarioRepository.getReferenceById(usuario.getId());
        var questionario = new QuestionarioPerfilRisco(
                usuario,
                dados.pergunta1(),
                dados.pergunta2(),
                dados.pergunta3(),
                dados.pergunta4(),
                dados.pergunta5(),
                dados.pergunta6(),
                dados.pergunta7());

        var categoria = determinarCategoria(questionario);
        var perfil = new PerfilRisco(usuario, categoria);

        perfilRiscoRepository.save(perfil);
        questionarioPerfilRiscoRepository.save(questionario);

        return new DadosPerfilRisco(perfil, questionario);
    }

    public PerfilRisco carregarPerfilRisco(Usuario usuario) {
        return perfilRiscoRepository.findByUsuarioId(usuario.getId());
    }

    public QuestionarioPerfilRisco carregarQuestionarioPerfilRisco(Usuario usuario) {
        return questionarioPerfilRiscoRepository.findByUsuarioId(usuario.getId());
    }

    public void resetarQuestionario(Usuario usuario) {
        var perfilRisco = perfilRiscoRepository.findByUsuarioId(usuario.getId());
        var questionario = questionarioPerfilRiscoRepository.findByUsuarioId(usuario.getId());
        perfilRiscoRepository.delete(perfilRisco);
        questionarioPerfilRiscoRepository.delete(questionario);
    }

    private CategoriaPerfilRisco determinarCategoria(QuestionarioPerfilRisco questionario) {
        var pontuacao = 0;

        if (questionario.getPergunta1().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (questionario.getPergunta2().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (questionario.getPergunta3().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (questionario.getPergunta4().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (questionario.getPergunta5().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (questionario.getPergunta6().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (questionario.getPergunta7().equals('a')) {
            pontuacao += 1;
        } else {
            pontuacao += 2;
        }

        if (pontuacao <= 9) {
            return CategoriaPerfilRisco.CONSERVADOR;
        } else if (pontuacao <= 12) {
            return CategoriaPerfilRisco.MODERADO;
        } else {
            return CategoriaPerfilRisco.ARROJADO;
        }
    }

}
