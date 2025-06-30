import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from './login';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../usuarios/usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly API = `${environment.api}/login`

  constructor(private http: HttpClient) { }

  logar(login: Login): Observable<any> {
    return this.http.post<Login>(this.API, login);
  }

  cadastrar(usuario: Usuario): Observable<any> {
    return this.http.post<Login>(`${this.API}/cadastrar`, usuario);
  }

}
