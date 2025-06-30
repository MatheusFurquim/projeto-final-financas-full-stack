package br.com.matheus.financas.domain.repository;

import br.com.matheus.financas.domain.model.PerfilRisco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRiscoRepository extends JpaRepository<PerfilRisco, Long> {

    PerfilRisco findByUsuarioId(Long id);

}
