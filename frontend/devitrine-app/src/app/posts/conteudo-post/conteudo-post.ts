import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
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

  private route = inject(ActivatedRoute);
  private postService = inject(PostService);

  post$!: Observable<DetalhesPost>;

  ngOnInit(): void {
    this.post$ = this.route.queryParams.pipe(
      switchMap(params => this.postService.obterPorId(params['id']))
    );
  }
}