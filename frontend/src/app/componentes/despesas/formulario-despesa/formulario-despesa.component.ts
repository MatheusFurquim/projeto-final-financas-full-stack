import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router'
import { DespesaService } from '../despesa.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Despesa } from '../despesa';

@Component({
  selector: 'app-formulario-despesa',
  templateUrl: './formulario-despesa.component.html',
  styleUrls: ['./formulario-despesa.component.css']
})
export class FormularioDespesaComponent implements OnInit {

  formulario!: FormGroup

  constructor(
    private service: DespesaService,
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
    this.service.buscarPorId(id).subscribe((despesa) => {
      this.preencherFormulario(despesa)
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
    this.router.navigateByUrl('/despesas')
  }

  private preencherFormulario(despesa?: Despesa) {
    this.formulario = this.formBuilder.group({
      id: [despesa?.id],
      descricao: [despesa?.descricao, Validators.required],
      valor: [despesa?.valor, Validators.required],
      data: [despesa?.data, Validators.required]
    })
  }

}
