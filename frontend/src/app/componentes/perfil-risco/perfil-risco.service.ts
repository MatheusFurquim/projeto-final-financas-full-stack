import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { QuestionarioPerfil } from './questionario-perfil';
import { Observable } from 'rxjs';
import { DadosPerfilRisco } from './dados-perfil-risco';

@Injectable({
  providedIn: 'root'
})
export class PerfilRiscoService {

  private readonly API = `${environment.api}/perfil-risco`

  constructor(private http: HttpClient) { }

  cadastrar(questionario: QuestionarioPerfil): Observable<DadosPerfilRisco> {
    return this.http.post<DadosPerfilRisco>(this.API, questionario);
  }

  refazer(): Observable<void> {
    return this.http.post<void>(`${this.API}/refazer`, null);
  }

  carregarPerfilDoUsuario(): Observable<DadosPerfilRisco> {
    return this.http.get<DadosPerfilRisco>(this.API);
  }

}
