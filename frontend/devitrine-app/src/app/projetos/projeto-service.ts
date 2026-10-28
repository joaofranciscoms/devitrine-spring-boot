import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosProjetoForm, DetalhesProjeto } from './dados-projeto';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagination/page-result';

@Injectable({
  providedIn: 'root',
})
export class ProjetoService {

  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/projetos'
  tecnologiasUrl = 'http://localhost:8080/api/tecnologias'

  criar(dados: DadosProjetoForm) : Observable<DetalhesProjeto>{
      return this.http.post<DetalhesProjeto>(this.baseUrl, dados);
  }

  obterPorId(id: string) : Observable<DetalhesProjeto> {
    return this.http.get<DetalhesProjeto>(`${this.baseUrl}/${id}`);
  }

  listar(tipo: string = '', tecnologia: string = '', page: number = 0, size: number = 10) : Observable<PageResult<DetalhesProjeto>> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (tipo) {
      params = params.set('tipo', tipo);
    }
    if (tecnologia) {
      params = params.set('tecnologia', tecnologia);
    }
    return this.http.get<PageResult<DetalhesProjeto>>(this.baseUrl, { params });
  }

  listarTecnologias() : Observable<string[]> {
    return this.http.get<string[]>(this.tecnologiasUrl);
  }
}
