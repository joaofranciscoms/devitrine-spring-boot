import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosPostForm, DetalhesPost } from './dados-post';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagination/page-result';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  
  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/posts'

  criar(dados: DadosPostForm) : Observable<DetalhesPost>{
      return this.http.post<DetalhesPost>(this.baseUrl, dados);
  }

  obterPorId(id: string) : Observable<DetalhesPost> {
    return this.http.get<DetalhesPost>(`${this.baseUrl}/${id}`);
  }

  listar(page: number = 0, size: number = 10) : Observable<PageResult<DetalhesPost>> {
    return this.http.get<PageResult<DetalhesPost>>(`${this.baseUrl}?page=${page}&size=${size}`);
  }
}