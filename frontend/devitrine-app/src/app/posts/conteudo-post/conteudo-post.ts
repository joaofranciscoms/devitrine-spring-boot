import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Observable, switchMap, tap } from 'rxjs';
import { PostService } from '../post-service';
import { DetalhesPost } from '../dados-post';
import { PageResult } from '../../common/pagination/page-result';
import { DetalhesComentario } from '../../comentarios/dados-comentario';
import { ComentarioService } from '../../comentarios/comentario-service';

@Component({
  selector: 'app-conteudo-post',
  imports: [CommonModule, RouterModule],
  templateUrl: './conteudo-post.html',
  styleUrl: './conteudo-post.scss',
})
export class ConteudoPost implements OnInit {

  route = inject(ActivatedRoute);
  postService = inject(PostService);
  comentarioService = inject(ComentarioService);
  router = inject(Router)
  paginaAtual = 0;
  tamanhoPagina = 5;
  postId!: string;

  post$!: Observable<DetalhesPost>
  listagem$!: Observable<PageResult<DetalhesComentario>>
  

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
        this.postId = params['id'];
        this.post$ = this.postService.obterPorId(this.postId);
        this.listarComentarios();
    });
  }

  comentar(idPost: string){
    this.router.navigate(['paginas/comentario-post'], {
      queryParams: {id: idPost}
    });    
  }
  
  listarComentarios(){
    this.listagem$ = this.comentarioService.listar(this.postId, this.paginaAtual, this.tamanhoPagina);
  }
  
  navegar(page: number){
    this.paginaAtual = page;
    this.listarComentarios();
  }
  
  paginas(totalPages: number) : number[]{
      return Array.from({length: totalPages}, (valor, index) => index);
  }
  
  registroInicial(listagem: PageResult<DetalhesComentario>){
    if(listagem.totalElements === 0){
      return 0;
    }
  
    return (listagem.number * listagem.size) + 1;
  }
  
  registroFinal(listagem: PageResult<DetalhesComentario>){
    if(listagem.totalElements === 0){
      return 0;
    }
  
    return Math.min( (listagem.number + 1) * listagem.size, listagem.totalElements);
  }
}