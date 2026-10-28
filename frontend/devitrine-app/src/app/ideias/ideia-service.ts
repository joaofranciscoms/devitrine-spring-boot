import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosIdeiaForm, DetalhesIdeia } from './dados-ideia';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagination/page-result';

@Injectable({
  providedIn: 'root',
})
export class IdeiaService {

  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/ideias'

  criar(dados: DadosIdeiaForm) : Observable<DetalhesIdeia>{
      return this.http.post<DetalhesIdeia>(this.baseUrl, dados);
  }

  obterPorId(id: string) : Observable<DetalhesIdeia> {
    return this.http.get<DetalhesIdeia>(`${this.baseUrl}/${id}`);
  }

  listar(page: number = 0, size: number = 10) : Observable<PageResult<DetalhesIdeia>> {
    return this.http.get<PageResult<DetalhesIdeia>>(`${this.baseUrl}?page=${page}&size=${size}`);
  }
}
