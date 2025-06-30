import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-formulario-usuario',
  templateUrl: './formulario-usuario.component.html',
  styleUrls: ['./formulario-usuario.component.css']
})
export class FormularioUsuarioComponent implements OnInit {

  formulario!: FormGroup

  constructor(
    private service: UsuarioService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.preencherFormulario(null!)

    this.activatedRoute.paramMap.subscribe((params: ParamMap) => {
      const id = params.get('id')
      if (id != null) {
        this.buscarPorId(Number(id))
      }
    })
  }

  private buscarPorId(id: number) {
    this.service.buscarPorId(id).subscribe((usuario) => {
      this.preencherFormulario(usuario)
    })
  }

  cadastrar() {
    if (!this.formulario.valid) {
      return
    }

    if (this.formulario.value.id != null) {
      this.service.atualizar(this.formulario.value).subscribe(() => {
        this.navegarParaListagem()
      })
    } else {
      this.service.cadastrar(this.formulario.value).subscribe(() => {
        this.navegarParaListagem()
      })
    }
  }

  private navegarParaListagem() {
    this.router.navigateByUrl('/admin/usuarios')
  }

  private preencherFormulario(usuario?: Usuario) {
    this.formulario = this.formBuilder.group({
      id: [usuario?.id],
      nome: [usuario?.nome, Validators.required],
      email: [usuario?.email, [Validators.required, Validators.email]],
      senha: [usuario?.senha, Validators.required],
      admin: [usuario?.admin, Validators.required]
    })
  }

}
