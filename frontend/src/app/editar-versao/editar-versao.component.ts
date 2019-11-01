import {Component, OnInit} from '@angular/core';
import {VersaoService} from "../_services/versao.service";
import {ActivatedRoute} from "@angular/router";
import {Versao} from "../_dto/versao";
import {Observable} from "rxjs";
import {Resposta} from "../_dto/resposta";
import {ToastService} from "../_services/toast.service";

@Component({
  selector: 'app-editar-versao',
  templateUrl: './editar-versao.component.html',
  styleUrls: ['./editar-versao.component.css']
})
export class EditarVersaoComponent implements OnInit {

  constructor(private versaoService: VersaoService, private route: ActivatedRoute,private toastService:ToastService) {
  }

  selectedId: string;
  isNew = true;
  versaoSelecionada: Versao =  new Versao();

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
        if (params.has('id')) {
          this.selectedId = params.get('id');
          this.isNew = false;
          this.carregar(this.selectedId);
        }else{
          this.versaoSelecionada = new Versao();
        }
      }
    )
  }

  carregar(id) {
    this.versaoService.getOneById(id).subscribe(retorno => {
      if(retorno.sucesso){
        this.versaoSelecionada = retorno.resposta;
      }
    });
  }

  salvar() {
    const versao = this.versaoSelecionada;
    let resposta: Observable<Resposta> = null;
    if(versao.id){
      resposta = this.versaoService.update(versao.id.toString(),versao);
    }else{
      resposta = this.versaoService.insert(versao);
    }
    if(resposta){
      resposta.subscribe(resposta => {
          if(resposta.sucesso){
            this.versaoSelecionada = resposta.resposta;
            this.toastService.showInfo('Sucesso! ',resposta.mensagem)
          }else{
            this.toastService.showError('Erro!',resposta.mensagem)
          }
      });
    }
  }



}
