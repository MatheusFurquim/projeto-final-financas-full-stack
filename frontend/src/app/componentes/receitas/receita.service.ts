import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Receita } from './receita';
import { Observable } from 'rxjs';
import { Paginacao } from '../paginacao/paginacao';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReceitaService {

  private readonly API = `${environment.api}/receitas`

  constructor(private http: HttpClient) { }

  listar(): Observable<Paginacao> {
    return this.http.get<Paginacao>(this.API);
  }

  buscarPorId(id: number): Observable<Receita> {
    return this.http.get<Receita>(`${this.API}/${id}`)
  }

  cadastrar(receita: Receita): Observable<Receita> {
    return this.http.post<Receita>(this.API, receita)
  }

  atualizar(receita: Receita): Observable<Receita> {
    return this.http.put<Receita>(`${this.API}/${receita.id}`, receita)
  }

  excluir(id: number): Observable<Receita> {
    return this.http.delete<Receita>(`${this.API}/${id}`)
  }

}
