import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { Paginacao } from '../paginacao/paginacao';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private readonly API = `${environment.api}/admin/usuarios`

  constructor(private http: HttpClient) { }

  listar(): Observable<Paginacao> {
    return this.http.get<Paginacao>(this.API);
  }

  buscarPorId(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.API}/${id}`)
  }

  cadastrar(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.API, usuario)
  }

  atualizar(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.API}/${usuario.id}`, usuario)
  }

  excluir(id: number): Observable<Usuario> {
    return this.http.delete<Usuario>(`${this.API}/${id}`)
  }

}
