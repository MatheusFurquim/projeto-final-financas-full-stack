import { Injectable } from '@angular/core';
import { Usuario } from '../usuarios/usuario';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private itemName = 'jwt'
  usuarioLogado?: Usuario

  guardarToken(token: string) {
    localStorage.setItem(this.itemName, token)
    const dados = new JwtHelperService().decodeToken(token)
    this.preencherDadosUsuarioLogado(dados)
  }

  excluirToken() {
    localStorage.removeItem(this.itemName)
    this.preencherDadosUsuarioLogado(null)
  }

  atualizarDadosUsuarioLogado(usuario: Usuario) {
    const admin = this.usuarioLogado!.admin
    this.usuarioLogado = {
      nome: usuario?.nome,
      email: usuario?.email,
      admin: admin
    }
  }

  private preencherDadosUsuarioLogado(dados?: any) {
    this.usuarioLogado = {
      nome: dados?.nome,
      email: dados?.sub,
      admin: dados?.admin
    }
  }

}
