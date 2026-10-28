import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosColaboracaoForm, DetalhesColaboracao } from './dados-colaboracao';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagination/page-result';

@Injectable({
  providedIn: 'root',
})
export class ColaboracaoService {

  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/colaboracoes'

  criar(dados: DadosColaboracaoForm) : Observable<DetalhesColaboracao>{
    return this.http.post<DetalhesColaboracao>(this.baseUrl, dados);
  }

  listar(ideiaId: string, page: number = 0, size: number = 10): Observable<PageResult<DetalhesColaboracao>> {
    return this.http.get<PageResult<DetalhesColaboracao>>(`${this.baseUrl}?ideiaId=${ideiaId}&page=${page}&size=${size}`);
  }
}
