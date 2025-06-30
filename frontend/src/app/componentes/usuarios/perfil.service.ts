import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private readonly API = `${environment.api}/perfil`

  constructor(private http: HttpClient) { }

  atualizar(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(this.API, usuario)
  }

}
