import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { catchError, EMPTY, Observable } from 'rxjs';
import { ProjetoService } from '../projeto-service';
import { PageResult } from '../../common/pagination/page-result';
import { descricaoTipoProjeto, DetalhesProjeto, TipoProjeto, TIPOS_PROJETO } from '../dados-projeto';
import { Header } from '../../common/components/header/header';

@Component({
  selector: 'app-listagem-projetos',
  imports: [CommonModule, Header],
  templateUrl: './listagem-projetos.html',
  styleUrl: './listagem-projetos.scss',
})
export class ListagemProjetos implements OnInit {
  router = inject(Router);
  service = inject(ProjetoService);

  listagem$!: Observable<PageResult<DetalhesProjeto>>;
  tecnologias$!: Observable<string[]>;
  tipos = TIPOS_PROJETO;
  tipoSelecionado = '';
  tecnologiaSelecionada = '';
  paginaAtual = 0;
  tamanhoPagina = 5;
  erro = false;

  ngOnInit(): void {
    this.tecnologias$ = this.service.listarTecnologias();
    this.listarProjetos();
  }

  criarProjeto() {
    this.router.navigate(['paginas/cadastro-projeto']);
  }

  listarProjetos() {
    this.erro = false;
    this.listagem$ = this.service.listar(this.tipoSelecionado, this.tecnologiaSelecionada, this.paginaAtual, this.tamanhoPagina).pipe(
      catchError((err) => {
        console.error(err);
        this.erro = true;
        return EMPTY;
      })
    );
  }

  filtrarTipo(tipo: string) {
    this.tipoSelecionado = tipo;
    this.paginaAtual = 0;
    this.listarProjetos();
  }

  filtrarTecnologia(tecnologia: string) {
    this.tecnologiaSelecionada = tecnologia;
    this.paginaAtual = 0;
    this.listarProjetos();
  }

  descricaoTipo(tipo: TipoProjeto) {
    return descricaoTipoProjeto(tipo);
  }

  navegar(page: number) {
    this.paginaAtual = page;
    this.listarProjetos();
  }

  paginas(totalPages: number): number[] {
    return Array.from({ length: totalPages }, (_, index) => index);
  }

  registroInicial(listagem: PageResult<DetalhesProjeto>) {
    if (listagem.totalElements === 0) return 0;
    return listagem.number * listagem.size + 1;
  }

  registroFinal(listagem: PageResult<DetalhesProjeto>) {
    if (listagem.totalElements === 0) return 0;
    return Math.min((listagem.number + 1) * listagem.size, listagem.totalElements);
  }

  visualizarProjeto(idProjeto: string) {
    this.router.navigate(['paginas/conteudo-projeto'], {
      queryParams: { id: idProjeto },
    });
  }
}
