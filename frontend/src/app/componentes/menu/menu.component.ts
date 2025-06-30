import { Component } from '@angular/core';
import { TokenService } from '../login/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  constructor(
    private tokenService: TokenService,
    private router: Router
  ) {}

  deslogar() {
    this.tokenService.excluirToken()
    this.router.navigateByUrl('/login')
  }

  admin() {
    return this.tokenService.usuarioLogado?.admin
  }

}
