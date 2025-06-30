package br.com.matheus.financas.domain.service;

import br.com.matheus.financas.domain.model.Despesa;
import br.com.matheus.financas.domain.model.Receita;
import br.com.matheus.financas.domain.model.Usuario;
import br.com.matheus.financas.domain.model.dto.SaldoMensal;
import br.com.matheus.financas.domain.repository.DespesaRepository;
import br.com.matheus.financas.domain.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RelatorioFinanceiroService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;


    public List<SaldoMensal> gerarRelatorioMensal(Usuario usuario) {
        var receitas = receitaRepository.findAllByUsuarioId(usuario.getId());
        var despesas = despesaRepository.findAllByUsuarioId(usuario.getId());

        var totalReceitasPorMes = receitas.stream()
                .collect(Collectors.groupingBy(receita -> YearMonth.from(receita.getData()),
                        Collectors.mapping(Receita::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        var totalDespesasPorMes = despesas.stream()
                .collect(Collectors.groupingBy(despesa -> YearMonth.from(despesa.getData()),
                        Collectors.mapping(Despesa::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        var allMonths = Stream
                .concat(totalReceitasPorMes.keySet().stream(), totalDespesasPorMes.keySet().stream())
                .collect(Collectors.toSet());

        return allMonths.stream()
                .sorted()
                .map(mes -> {
                    var totalReceitas = totalReceitasPorMes.getOrDefault(mes, BigDecimal.ZERO);
                    var totalDespesas = totalDespesasPorMes.getOrDefault(mes, BigDecimal.ZERO);
                    var saldo = totalReceitas.subtract(totalDespesas);
                    return new SaldoMensal(mes, totalReceitas, totalDespesas, saldo);
                })
                .collect(Collectors.toList());
    }

}
