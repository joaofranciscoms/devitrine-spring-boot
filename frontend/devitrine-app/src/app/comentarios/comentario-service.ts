import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosComentarioForm, DetalhesComentario } from './dados-comentario';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagination/page-result';

@Injectable({
  providedIn: 'root',
})
export class ComentarioService {
  
  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/comentarios'

  criar(dados: DadosComentarioForm) : Observable<DetalhesComentario>{
    return this.http.post<DetalhesComentario>(this.baseUrl, dados);
  }

  listar(postId: string, page: number = 0, size: number = 10): Observable<PageResult<DetalhesComentario>> {
    return this.http.get<PageResult<DetalhesComentario>>(`${this.baseUrl}?postId=${postId}&page=${page}&size=${size}`);
}
}