import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { IdeiaService } from '../ideia-service';
import { DetalhesIdeia } from '../dados-ideia';
import { PageResult } from '../../common/pagination/page-result';
import { DetalhesColaboracao } from '../../colaboracoes/dados-colaboracao';
import { ColaboracaoService } from '../../colaboracoes/colaboracao-service';

@Component({
  selector: 'app-conteudo-ideia',
  imports: [CommonModule, RouterModule],
  templateUrl: './conteudo-ideia.html',
  styleUrl: './conteudo-ideia.scss',
})
export class ConteudoIdeia implements OnInit {

  route = inject(ActivatedRoute);
  ideiaService = inject(IdeiaService);
  colaboracaoService = inject(ColaboracaoService);
  router = inject(Router)
  paginaAtual = 0;
  tamanhoPagina = 5;
  ideiaId!: string;

  ideia$!: Observable<DetalhesIdeia>
  listagem$!: Observable<PageResult<DetalhesColaboracao>>

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
        this.ideiaId = params['id'];
        this.ideia$ = this.ideiaService.obterPorId(this.ideiaId);
        this.listarColaboracoes();
    });
  }

  colaborar(idIdeia: string){
    this.router.navigate(['paginas/colaboracao-ideia'], {
      queryParams: {id: idIdeia}
    });
  }

  listarColaboracoes(){
    this.listagem$ = this.colaboracaoService.listar(this.ideiaId, this.paginaAtual, this.tamanhoPagina);
  }

  navegar(page: number){
    this.paginaAtual = page;
    this.listarColaboracoes();
  }

  paginas(totalPages: number) : number[]{
      return Array.from({length: totalPages}, (valor, index) => index);
  }

  registroInicial(listagem: PageResult<DetalhesColaboracao>){
    if(listagem.totalElements === 0){
      return 0;
    }

    return (listagem.number * listagem.size) + 1;
  }

  registroFinal(listagem: PageResult<DetalhesColaboracao>){
    if(listagem.totalElements === 0){
      return 0;
    }

    return Math.min( (listagem.number + 1) * listagem.size, listagem.totalElements);
  }
}
