import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaReceitasComponent } from './componentes/receitas/lista-receitas/lista-receitas.component';
import { FormularioReceitaComponent } from './componentes/receitas/formulario-receita/formulario-receita.component';
import { ListaDespesasComponent } from './componentes/despesas/lista-despesas/lista-despesas.component';
import { FormularioDespesaComponent } from './componentes/despesas/formulario-despesa/formulario-despesa.component';
import { ListaUsuariosComponent } from './componentes/usuarios/lista-usuarios/lista-usuarios.component';
import { FormularioUsuarioComponent } from './componentes/usuarios/formulario-usuario/formulario-usuario.component';
import { PerfilUsuarioComponent } from './componentes/usuarios/perfil-usuario/perfil-usuario.component';
import { FormularioLoginComponent } from './componentes/login/formulario-login/formulario-login.component';
import { loginGuard } from './componentes/login/login.guard';
import { PerfilComponent } from './componentes/perfil-risco/perfil/perfil.component';
import { RelatorioFinanceiroComponent } from './componentes/relatorio-financeiro/relatorio-financeiro.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'receitas',
    component: ListaReceitasComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'receitas/formulario',
    component: FormularioReceitaComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'receitas/:id',
    component: FormularioReceitaComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'despesas',
    component: ListaDespesasComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'despesas/formulario',
    component: FormularioDespesaComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'despesas/:id',
    component: FormularioDespesaComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'admin/usuarios',
    component: ListaUsuariosComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'admin/usuarios/formulario',
    component: FormularioUsuarioComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'admin/usuarios/:id',
    component: FormularioUsuarioComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'perfil',
    component: PerfilUsuarioComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'perfil-risco',
    component: PerfilComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'relatorio-financeiro',
    component: RelatorioFinanceiroComponent,
    canActivate: [loginGuard]
  },
  {
    path: 'login',
    component: FormularioLoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
