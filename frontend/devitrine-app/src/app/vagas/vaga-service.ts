import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosVagaForm, DetalhesVaga } from './dados-vaga';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagination/page-result';

@Injectable({
  providedIn: 'root',
})
export class VagaService {
  
  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/vagas'

  criar(dados: DadosVagaForm) : Observable<DetalhesVaga>{
      return this.http.post<DetalhesVaga>(this.baseUrl, dados);
  }

  obterPorId(id: string) : Observable<DetalhesVaga> {
    return this.http.get<DetalhesVaga>(`${this.baseUrl}/${id}`);
  }

  listar(page: number = 0, size: number = 10) : Observable<PageResult<DetalhesVaga>> {
    return this.http.get<PageResult<DetalhesVaga>>(`${this.baseUrl}?page=${page}&size=${size}`);
  }
}