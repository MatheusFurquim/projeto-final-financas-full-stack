import { TokenService } from './../../login/token.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PerfilService } from '../perfil.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit {

  formulario!: FormGroup

  constructor(
    private perfilService: PerfilService,
    private tokenService: TokenService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      nome: [this.tokenService.usuarioLogado?.nome, Validators.required],
      email: [this.tokenService.usuarioLogado?.email, [Validators.required, Validators.email]],
      senha: [null, Validators.required],
      confirmacaoSenha: [null, Validators.required]
    })
  }

  atualizar() {
    if (!this.formulario.valid) {
      return
    }

    this.perfilService.atualizar(this.formulario.value).subscribe({
      next: (dados) => {
        this.tokenService.atualizarDadosUsuarioLogado(dados);
        this.router.navigateByUrl('/receitas')
      }
    })
  }

}
