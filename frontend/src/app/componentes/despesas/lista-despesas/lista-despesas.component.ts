import { Component, OnInit } from '@angular/core';
import { Despesa } from '../despesa';
import { DespesaService } from '../despesa.service';

@Component({
  selector: 'app-lista-despesas',
  templateUrl: './lista-despesas.component.html',
  styleUrls: ['./lista-despesas.component.css']
})
export class ListaDespesasComponent implements OnInit {

  despesas: Despesa[] = []

  constructor(private service: DespesaService) {}

  ngOnInit(): void {
    this.service.listar().subscribe((paginacao) => {
      this.despesas = paginacao.content
    })
  }

  excluir(id: number) {
    this.service.excluir(id).subscribe(() => {
      const index = this.despesas.findIndex(despesa => despesa.id === id);
      if (index !== -1) {
        this.despesas.splice(index, 1)
      }
    })
  }

}
