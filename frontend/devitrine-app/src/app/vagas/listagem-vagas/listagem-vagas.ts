import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { catchError, EMPTY, Observable } from 'rxjs';
import { VagaService } from '../vaga-service';
import { PageResult } from '../../common/pagination/page-result';
import { DetalhesVaga } from '../dados-vaga';
import { Header } from '../../common/components/header/header';
import { AuthService } from '../../auth/auth-service';

@Component({
  selector: 'app-listagem-vagas',
  imports: [CommonModule, Header],
  templateUrl: './listagem-vagas.html',
  styleUrl: './listagem-vagas.scss',
})
export class ListagemVagas implements OnInit {
  router = inject(Router);
  service = inject(VagaService);
  auth = inject(AuthService)

  listagem$!: Observable<PageResult<DetalhesVaga>>;
  paginaAtual = 0;
  tamanhoPagina = 5;
  erro = false;

  ngOnInit(): void {
    this.listarVagas();
  }

  criarVaga() {
    this.router.navigate(['paginas/cadastro-vaga']);
  }

  listarVagas() {
    this.erro = false;
    this.listagem$ = this.service.listar(this.paginaAtual, this.tamanhoPagina).pipe(
      catchError((err) => {
        console.error(err);
        this.erro = true;
        return EMPTY;
      })
    );
  }

  navegar(page: number) {
    this.paginaAtual = page;
    this.listarVagas();
  }

  paginas(totalPages: number): number[] {
    return Array.from({ length: totalPages }, (_, index) => index);
  }

  registroInicial(listagem: PageResult<DetalhesVaga>) {
    if (listagem.totalElements === 0) return 0;
    return listagem.number * listagem.size + 1;
  }

  registroFinal(listagem: PageResult<DetalhesVaga>) {
    if (listagem.totalElements === 0) return 0;
    return Math.min((listagem.number + 1) * listagem.size, listagem.totalElements);
  }

  visualizarVaga(idVaga: string) {
    this.router.navigate(['paginas/conteudo-vaga'], {
      queryParams: { id: idVaga },
    });
  }
}