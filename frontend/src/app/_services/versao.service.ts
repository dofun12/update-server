import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Resposta} from "../_dto/resposta";

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
}
