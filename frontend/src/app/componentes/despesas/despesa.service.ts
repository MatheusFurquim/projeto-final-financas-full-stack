import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Despesa } from './despesa';
import { Observable } from 'rxjs';
import { Paginacao } from '../paginacao/paginacao';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DespesaService {

  private readonly API = `${environment.api}/despesas`

  constructor(private http: HttpClient) { }

  listar(): Observable<Paginacao> {
    return this.http.get<Paginacao>(this.API);
  }

  buscarPorId(id: number): Observable<Despesa> {
    return this.http.get<Despesa>(`${this.API}/${id}`)
  }

  cadastrar(despesa: Despesa): Observable<Despesa> {
    return this.http.post<Despesa>(this.API, despesa)
  }

  atualizar(despesa: Despesa): Observable<Despesa> {
    return this.http.put<Despesa>(`${this.API}/${despesa.id}`, despesa)
  }

  excluir(id: number): Observable<Despesa> {
    return this.http.delete<Despesa>(`${this.API}/${id}`)
  }

}
