import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { PostService } from '../post-service';
import { DetalhesPost } from '../dados-post';

@Component({
  selector: 'app-conteudo-post',
  imports: [CommonModule, RouterModule],
  templateUrl: './conteudo-post.html',
  styleUrl: './conteudo-post.scss',
})
export class ConteudoPost implements OnInit {

  route = inject(ActivatedRoute);
  service = inject(PostService);
  router = inject(Router)

  post$!: Observable<DetalhesPost>

  ngOnInit(): void {
    this.post$ = this.route.queryParams.pipe(
      switchMap(params => this.service.obterPorId(params['id']))
    );
  }

  comentar(idPost: string){
    this.router.navigate(['paginas/comentario-post'], {
      queryParams: {id: idPost}
    });    
  }
}