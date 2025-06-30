import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent implements OnInit {

  usuarios: Usuario[] = []

  constructor(private service: UsuarioService) {}

  ngOnInit(): void {
    this.service.listar().subscribe((paginacao) => {
      this.usuarios = paginacao.content
    })
  }

  excluir(id: number) {
    this.service.excluir(id).subscribe(() => {
      const index = this.usuarios.findIndex(usuario => usuario.id === id);
      if (index !== -1) {
        this.usuarios.splice(index, 1)
      }
    })
  }

}
