import { RelatorioFinanceiro } from './relatorio-financeiro';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RelatorioFinanceiroService {

  private readonly API = `${environment.api}/relatorio-financeiro`

  constructor(private http: HttpClient) { }

  gerarRelatorioFinanceiro(): Observable<RelatorioFinanceiro[]> {
    return this.http.get<RelatorioFinanceiro[]>(this.API);
  }

}
