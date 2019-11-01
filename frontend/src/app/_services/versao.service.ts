import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Resposta} from "../_dto/resposta";
import {Versao} from "../_dto/versao";

@Injectable({
  providedIn: 'root'
})
export class VersaoService {

  constructor(private http: HttpClient) {}

  name = 'versao';

  getList(){
    return this.http.get<Resposta>(`${environment.baseurl}/${this.name}`);
  }

  getListByTitle(title: string){
    return this.http.get<Resposta>(`${environment.baseurl}/${this.name}/versao/${title}`);
  }

  getOneById(id: string){
    return this.http.get<Resposta>(`${environment.baseurl}/${this.name}/${id}`);
  }

  insert(versao: Versao){
    return this.http.post<Resposta>(`${environment.baseurl}/${this.name}`,versao);
  }

  update(id: string,versao: Versao){
    return this.http.put<Resposta>(`${environment.baseurl}/${this.name}/${id}`,versao);
  }
}
