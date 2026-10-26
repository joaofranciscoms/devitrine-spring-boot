import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { VagaService } from '../vaga-service';
import { DetalhesVaga } from '../dados-vaga';

@Component({
  selector: 'app-conteudo-vaga',
  imports: [CommonModule, RouterModule],
  templateUrl: './conteudo-vaga.html',
  styleUrl: './conteudo-vaga.scss',
})
export class ConteudoVaga implements OnInit {
  route = inject(ActivatedRoute);
  service = inject(VagaService);

  vagaId!: string;
  vaga$!: Observable<DetalhesVaga>;

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.vagaId = params['id'];
      this.vaga$ = this.service.obterPorId(this.vagaId).pipe(
        tap({ error: (err) => console.error('Erro ao obter vaga:', err) })
      );
    });
  }
}