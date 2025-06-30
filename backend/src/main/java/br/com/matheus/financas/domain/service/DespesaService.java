package br.com.matheus.financas.domain.service;

import br.com.matheus.financas.domain.model.Despesa;
import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosCadastroDespesa;
import br.com.matheus.financas.domain.model.dto.DadosDespesa;
import br.com.matheus.financas.domain.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository repository;

    public DadosDespesa cadastrar(DadosCadastroDespesa dados, Usuario usuario) {
        var despesa = new Despesa(dados, usuario);
        this.repository.save(despesa);
        return new DadosDespesa(despesa);
    }

    public Page<DadosDespesa> listar(Pageable paginacao, Usuario usuario) {
        var despesas = this.repository.findAllByUsuarioId(usuario.getId(), paginacao);
        return despesas.map(DadosDespesa::new);
    }

    public DadosDespesa buscarPorId(Long id) {
        var despesa = this.repository.findById(id).get();
        return new DadosDespesa(despesa);
    }

    public DadosDespesa atualizar(Long id, DadosCadastroDespesa dados) {
        var despesa = this.repository.findById(id).get();
        despesa.atualizarDados(dados);
        this.repository.save(despesa);
        return new DadosDespesa(despesa);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

}
