import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListVersaoComponent} from "./list-versao/list-versao.component";
import {EditarVersaoComponent} from "./editar-versao/editar-versao.component";


const routes: Routes = [
  {path: 'list-versao', component: ListVersaoComponent},
  {path: 'editar-versao/:id', component: EditarVersaoComponent},
  {path: 'nova-versao', component: EditarVersaoComponent},
  {path: '', redirectTo: 'list-versao', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
