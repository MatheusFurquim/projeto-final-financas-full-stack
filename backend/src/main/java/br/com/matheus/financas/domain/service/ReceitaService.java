package br.com.matheus.financas.domain.service;

import br.com.matheus.financas.domain.model.Receita;
import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.DadosCadastroReceita;
import br.com.matheus.financas.domain.model.dto.DadosReceita;
import br.com.matheus.financas.domain.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    public DadosReceita cadastrar(DadosCadastroReceita dados, Usuario usuario) {
        var receita = new Receita(dados, usuario);
        this.repository.save(receita);
        return new DadosReceita(receita);
    }

    public Page<DadosReceita> listar(Pageable paginacao, Usuario usuario) {
        var receitas = this.repository.findAllByUsuarioId(usuario.getId(), paginacao);
        return receitas.map(DadosReceita::new);
    }

    public DadosReceita buscarPorId(Long id) {
        var receita = this.repository.findById(id).get();
        return new DadosReceita(receita);
    }

    public DadosReceita atualizar(Long id, DadosCadastroReceita dados) {
        var receita = this.repository.findById(id).get();
        receita.atualizarDados(dados);
        this.repository.save(receita);
        return new DadosReceita(receita);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

}
