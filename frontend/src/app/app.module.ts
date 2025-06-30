import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './componentes/menu/menu.component';
import { ListaReceitasComponent } from './componentes/receitas/lista-receitas/lista-receitas.component';
import { FormularioReceitaComponent } from './componentes/receitas/formulario-receita/formulario-receita.component';
import { FormularioDespesaComponent } from './componentes/despesas/formulario-despesa/formulario-despesa.component';
import { ListaDespesasComponent } from './componentes/despesas/lista-despesas/lista-despesas.component';
import { ListaUsuariosComponent } from './componentes/usuarios/lista-usuarios/lista-usuarios.component';
import { FormularioUsuarioComponent } from './componentes/usuarios/formulario-usuario/formulario-usuario.component';
import { PerfilUsuarioComponent } from './componentes/usuarios/perfil-usuario/perfil-usuario.component';
import { FormularioLoginComponent } from './componentes/login/formulario-login/formulario-login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JwtModule } from '@auth0/angular-jwt';
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData, CurrencyPipe } from '@angular/common';
import { CURRENCY_MASK_CONFIG, CurrencyMaskConfig, CurrencyMaskModule } from "ng2-currency-mask";
import { HttpErrorInterceptor } from './http-error.interceptor';
import { MensagemComponent } from './componentes/mensagens/mensagem/mensagem.component';
import { PerfilComponent } from './componentes/perfil-risco/perfil/perfil.component';
import { environment } from 'src/environments/environment';
import { RelatorioFinanceiroComponent } from './componentes/relatorio-financeiro/relatorio-financeiro.component';

registerLocaleData(ptBr);
export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: 'left',
  allowNegative: false,
  decimal: ',',
  precision: 2,
  prefix: '',
  suffix: '',
  thousands: '.'
};

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ListaReceitasComponent,
    FormularioReceitaComponent,
    FormularioDespesaComponent,
    ListaDespesasComponent,
    ListaUsuariosComponent,
    FormularioUsuarioComponent,
    PerfilUsuarioComponent,
    FormularioLoginComponent,
    MensagemComponent,
    PerfilComponent,
    RelatorioFinanceiroComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CurrencyMaskModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ['localhost:8080', environment.api]
      },
    }),
  ],
  providers: [
    CurrencyPipe,
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' },
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig },
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }

export function tokenGetter() {
  return localStorage.getItem('jwt');
}
