package br.com.matheus.financas.domain.repository;

import br.com.matheus.financas.domain.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findAllByUsuarioId(Long id);

    Page<Despesa> findAllByUsuarioId(Long id, Pageable paginacao);

}
