import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { RelatorioFinanceiro } from './relatorio-financeiro';
import { RelatorioFinanceiroService } from './relatorio-financeiro.service';

@Component({
  selector: 'app-relatorio-financeiro',
  templateUrl: './relatorio-financeiro.component.html',
  styleUrls: ['./relatorio-financeiro.component.css']
})
export class RelatorioFinanceiroComponent implements OnInit {

  relatorio: RelatorioFinanceiro[] = []
  grafico: any

  constructor(private service: RelatorioFinanceiroService) {}

  ngOnInit(): void {
    this.service.gerarRelatorioFinanceiro().subscribe((relatorio) => {
      this.relatorio = relatorio
      this.gerarGrafico()
    })
  }

  gerarGrafico() {
    let totalReceitas = 0;
    let totalDespesas = 0;
    let totalSaldo = 0;
    this.relatorio.forEach(item => {
      totalReceitas += item.totalReceitas;
      totalDespesas += item.totalDespesas;
      totalSaldo += item.saldo;
    });

    this.grafico = new Chart("grafico", {
      type: 'pie',

      data: {
        labels: ['Receitas', 'Despesas', 'Saldo'],
	      datasets: [
        {
            label: 'Dataset',
            data: [totalReceitas, totalDespesas, totalSaldo],
            backgroundColor: ['rgb(54, 162, 235)', 'rgb(255, 99, 132)', 'rgb(75, 192, 192)'],
            hoverOffset: 4
        }]
      }
    });
  }

}
