import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router'
import { ReceitaService } from '../receita.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Receita } from '../receita';

@Component({
  selector: 'app-formulario-receita',
  templateUrl: './formulario-receita.component.html',
  styleUrls: ['./formulario-receita.component.css']
})
export class FormularioReceitaComponent implements OnInit {

  formulario!: FormGroup

  constructor(
    private service: ReceitaService,
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
    this.service.buscarPorId(id).subscribe((receita) => {
     this.preencherFormulario(receita)
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
    this.router.navigateByUrl('/receitas')
  }

  private preencherFormulario(receita?: Receita) {
    this.formulario = this.formBuilder.group({
      id: [receita?.id],
      descricao: [receita?.descricao, Validators.required],
      valor: [receita?.valor, Validators.required],
      data: [receita?.data, Validators.required]
    })
  }

}
