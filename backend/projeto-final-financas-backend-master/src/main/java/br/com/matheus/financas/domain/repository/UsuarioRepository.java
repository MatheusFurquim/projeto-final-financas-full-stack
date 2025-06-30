package br.com.matheus.financas.domain.repository;

import br.com.matheus.financas.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, Long id);

}
