package br.com.matheus.financas.domain.repository;

import br.com.matheus.financas.domain.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findAllByUsuarioId(Long id);

    Page<Receita> findAllByUsuarioId(Long id, Pageable paginacao);

}
