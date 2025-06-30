import { DadosPerfilRisco } from './../dados-perfil-risco';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PerfilRiscoService } from '../perfil-risco.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  formulario!: FormGroup
  categoria?: string

  constructor(private service: PerfilRiscoService, private formBuilder: FormBuilder) {
    this.formulario = this.formBuilder.group({
      pergunta1: ['', Validators.required],
      pergunta2: ['', Validators.required],
      pergunta3: ['', Validators.required],
      pergunta4: ['', Validators.required],
      pergunta5: ['', Validators.required],
      pergunta6: ['', Validators.required],
      pergunta7: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.service.carregarPerfilDoUsuario().subscribe((dadosPerfilRisco) => {
      this.preencherFormulario(dadosPerfilRisco)
    })
  }

  cadastrar() {
    if (!this.formulario.valid) {
      return
    }

    this.service.cadastrar(this.formulario.value).subscribe((dadosPerfilRisco) => {
      this.preencherFormulario(dadosPerfilRisco)
    })
  }

  refazer() {
    this.service.refazer().subscribe()
    this.preencherFormulario(null!)
  }

  private preencherFormulario(dadosPerfilRisco?: DadosPerfilRisco) {
    this.categoria = dadosPerfilRisco?.categoria
    this.formulario.patchValue({
      pergunta1: dadosPerfilRisco?.pergunta1,
      pergunta2: dadosPerfilRisco?.pergunta2,
      pergunta3: dadosPerfilRisco?.pergunta3,
      pergunta4: dadosPerfilRisco?.pergunta4,
      pergunta5: dadosPerfilRisco?.pergunta5,
      pergunta6: dadosPerfilRisco?.pergunta6,
      pergunta7: dadosPerfilRisco?.pergunta7,
    });
  }

}
