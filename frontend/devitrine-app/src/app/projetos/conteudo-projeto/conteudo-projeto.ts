import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { ProjetoService } from '../projeto-service';
import { descricaoTipoProjeto, DetalhesProjeto, TipoProjeto } from '../dados-projeto';

@Component({
  selector: 'app-conteudo-projeto',
  imports: [CommonModule, RouterModule],
  templateUrl: './conteudo-projeto.html',
  styleUrl: './conteudo-projeto.scss',
})
export class ConteudoProjeto implements OnInit {
  route = inject(ActivatedRoute);
  service = inject(ProjetoService);

  projetoId!: string;
  projeto$!: Observable<DetalhesProjeto>;

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.projetoId = params['id'];
      this.projeto$ = this.service.obterPorId(this.projetoId).pipe(
        tap({ error: (err) => console.error('Erro ao obter projeto:', err) })
      );
    });
  }

  descricaoTipo(tipo: TipoProjeto) {
    return descricaoTipoProjeto(tipo);
  }
}
