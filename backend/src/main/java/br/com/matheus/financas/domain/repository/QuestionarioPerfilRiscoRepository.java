package br.com.matheus.financas.domain.repository;

import br.com.matheus.financas.domain.model.QuestionarioPerfilRisco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionarioPerfilRiscoRepository extends JpaRepository<QuestionarioPerfilRisco, Long> {

    QuestionarioPerfilRisco findByUsuarioId(Long id);

}
