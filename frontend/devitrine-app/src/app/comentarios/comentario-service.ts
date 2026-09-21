import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DadosComentarioForm, DetalhesComentario } from './dados-comentario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ComentarioService {
  
  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/comentarios'

  criar(dados: DadosComentarioForm) : Observable<DetalhesComentario>{
    return this.http.post<DetalhesComentario>(this.baseUrl, dados);
  }
}