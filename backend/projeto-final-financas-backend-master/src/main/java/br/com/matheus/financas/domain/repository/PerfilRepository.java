package br.com.matheus.financas.domain.repository;

import br.com.matheus.financas.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByNome(String nome);

}
