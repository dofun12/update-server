import { Component, OnInit } from '@angular/core';
import {VersaoService} from "../_services/versao.service";
import {Versao} from "../_dto/versao";

@Component({
  selector: 'app-list-versao',
  templateUrl: './list-versao.component.html',
  styleUrls: ['./list-versao.component.css']
})
export class ListVersaoComponent implements OnInit {

  constructor(private versaoService: VersaoService) { }

  list: Versao[];

  ngOnInit() {
    this.getList();
  }

  getList(){
    this.versaoService.getList().subscribe(resposta => {
      if(resposta.sucesso){
        this.list = resposta.resposta;
      }
    });
  }

}
