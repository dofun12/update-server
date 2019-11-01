import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListVersaoComponent} from "./list-versao/list-versao.component";


const routes: Routes = [
  {path: 'list-versao', component: ListVersaoComponent},
  {path: '', redirectTo: 'list-versao', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
