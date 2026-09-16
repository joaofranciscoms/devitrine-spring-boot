import { Component, inject, OnInit } from '@angular/core';
import { PostService } from '../post-service';
import { first, Observable } from 'rxjs';
import { PageResult } from '../../common/pagination/page-result';
import { DetalhesPost } from '../dados-post';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';

@Component({
  selector: 'app-listagem-cartoes',
  imports: [CommonModule],
  templateUrl: './listagem-posts.html',
  styleUrl: './listagem-posts.scss',
})
export class ListagemPosts implements OnInit {
  
  router = inject(Router)
  service = inject(PostService)
  listagem$!: Observable<PageResult<DetalhesPost>>
  paginaAtual = 0;
  tamanhoPagina = 5;

  ngOnInit(): void {
    this.listarPosts();
  }

  listarPosts(){
    this.listagem$ = this.service.listar(this.paginaAtual, this.tamanhoPagina);
  }

  navegar(page: number){
    this.paginaAtual = page;
    this.listarPosts();
  }

  paginas(totalPages: number) : number[]{
      return Array.from({length: totalPages}, (valor, index) => index);
  }

  registroInicial(listagem: PageResult<DetalhesPost>){
    if(listagem.totalElements === 0){
      return 0;
    }

    return (listagem.number * listagem.size) + 1;
  }

  registroFinal(listagem: PageResult<DetalhesPost>){
    if(listagem.totalElements === 0){
      return 0;
    }

    return Math.min( (listagem.number + 1) * listagem.size, listagem.totalElements);
  }

  visualizarPost(idPost: string){
    this.router.navigate(['paginas/conteudo-post'], {
      queryParams: {id: idPost}
    });    
  }
}