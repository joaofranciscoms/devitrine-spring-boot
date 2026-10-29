import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DadosCandidaturaForm, DetalhesCandidatura } from './dados-candidatura';

@Injectable({
  providedIn: 'root',
})
export class CandidaturaService {

  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/candidaturas';

  criar(dados: DadosCandidaturaForm): Observable<DetalhesCandidatura> {
    return this.http.post<DetalhesCandidatura>(this.baseUrl, dados);
  }
}