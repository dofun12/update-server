import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListVersaoComponent } from './list-versao/list-versao.component';
import { MenuComponent } from './menu/menu.component';
import {HttpClientModule} from "@angular/common/http";
import { EditarVersaoComponent } from './editar-versao/editar-versao.component';
import {FormsModule} from "@angular/forms";
import { ToastComponent } from './toast/toast.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatPipe} from "./_pipes/dateFormatPipe";

@NgModule({
  declarations: [
    AppComponent,
    ListVersaoComponent,
    MenuComponent,
    EditarVersaoComponent,
    DateFormatPipe,
    ToastComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
