import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { TokenService } from '../token.service';
import { tokenGetter } from 'src/app/app.module';

@Component({
  selector: 'app-formulario-login',
  templateUrl: './formulario-login.component.html',
  styleUrls: ['./formulario-login.component.css']
})
export class FormularioLoginComponent implements OnInit {

  formulario!: FormGroup
  formularioCadastro!: FormGroup
  exibeFormularioCadastro = false
  cadastroRealizado = false

  constructor(
    private service: LoginService,
    private router: Router,
    private formBuilder: FormBuilder,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    if (tokenGetter() != null) {
      this.router.navigateByUrl('/receitas')
      return
    }

    this.formulario = this.formBuilder.group({
      email: [null, [Validators.required, Validators.email]],
      senha: [null, Validators.required]
    })

    this.formularioCadastro = this.formBuilder.group({
      nome: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      senha: [null, Validators.required],
      confirmacaoSenha: [null, Validators.required]
    })
  }

  logar() {
    if (!this.formulario.valid) {
      return;
    }

    this.service.logar(this.formulario.value).subscribe({
      next: (token) => {
        this.tokenService.guardarToken(token.token);
        this.router.navigateByUrl('/receitas')
      }
    })
  }

  cadastrar() {
    if (!this.formularioCadastro.valid) {
      return;
    }

    this.service.cadastrar(this.formularioCadastro.value).subscribe(() => {
      this.exibeFormularioCadastro = false
      this.cadastroRealizado = true
      this.limparFormularios()
    })
  }

  exibirFormularioCadastro() {
    this.exibeFormularioCadastro = true
    this.limparFormularios()
  }

  cancelarCadastro() {
    this.exibeFormularioCadastro = false
    this.limparFormularios()
  }

  limparFormularios() {
    this.formulario.patchValue({
      email: '',
      senha: ''
    })
    this.formularioCadastro.patchValue({
      nome: '',
      email: '',
      senha: '',
      confirmacaoSenha: ''
    })
  }

}
