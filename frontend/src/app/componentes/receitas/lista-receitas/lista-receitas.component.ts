import { Component, OnInit } from '@angular/core';
import { Receita } from '../receita';
import { ReceitaService } from '../receita.service';

@Component({
  selector: 'app-lista-receitas',
  templateUrl: './lista-receitas.component.html',
  styleUrls: ['./lista-receitas.component.css']
})
export class ListaReceitasComponent implements OnInit {

  receitas: Receita[] = []

  constructor(private service: ReceitaService) {}

  ngOnInit(): void {
    this.service.listar().subscribe((paginacao) => {
      this.receitas = paginacao.content
    })
  }

  excluir(id: number) {
    this.service.excluir(id).subscribe(() => {
      const index = this.receitas.findIndex(receita => receita.id === id);
      if (index !== -1) {
        this.receitas.splice(index, 1)
      }
    })
  }

}
