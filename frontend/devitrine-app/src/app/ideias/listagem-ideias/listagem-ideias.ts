import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { IdeiaService } from '../ideia-service';
import { PageResult } from '../../common/pagination/page-result';
import { DetalhesIdeia } from '../dados-ideia';
import { Header } from '../../common/components/header/header';

@Component({
  selector: 'app-listagem-ideias',
  imports: [CommonModule, Header],
  templateUrl: './listagem-ideias.html',
  styleUrl: './listagem-ideias.scss',
})
export class ListagemIdeias implements OnInit {
  router = inject(Router);
  service = inject(IdeiaService);

  listagem$!: Observable<PageResult<DetalhesIdeia>>;
  paginaAtual = 0;
  tamanhoPagina = 5;

  ngOnInit(): void {
    this.listarIdeias();
  }

  criarIdeia() {
    this.router.navigate(['paginas/cadastro-ideia']);
  }

  listarIdeias() {
    this.listagem$ = this.service.listar(this.paginaAtual, this.tamanhoPagina);
  }

  navegar(page: number) {
    this.paginaAtual = page;
    this.listarIdeias();
  }

  paginas(totalPages: number): number[] {
    return Array.from({ length: totalPages }, (_, index) => index);
  }

  registroInicial(listagem: PageResult<DetalhesIdeia>) {
    if (listagem.totalElements === 0) return 0;
    return listagem.number * listagem.size + 1;
  }

  registroFinal(listagem: PageResult<DetalhesIdeia>) {
    if (listagem.totalElements === 0) return 0;
    return Math.min((listagem.number + 1) * listagem.size, listagem.totalElements);
  }

  visualizarIdeia(idIdeia: string) {
    this.router.navigate(['paginas/conteudo-ideia'], {
      queryParams: { id: idIdeia },
    });
  }
}
