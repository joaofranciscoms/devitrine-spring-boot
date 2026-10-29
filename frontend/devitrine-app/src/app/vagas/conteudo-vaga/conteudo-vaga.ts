import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { VagaService } from '../vaga-service';
import { CandidaturaService } from '../candidatura-service';
import { DetalhesVaga } from '../dados-vaga';
import { AuthService } from '../../auth/auth-service'; // ajuste o caminho

@Component({
  selector: 'app-conteudo-vaga',
  imports: [CommonModule, RouterModule],
  templateUrl: './conteudo-vaga.html',
  styleUrl: './conteudo-vaga.scss',
})
export class ConteudoVaga implements OnInit {
  route = inject(ActivatedRoute);
  service = inject(VagaService);
  candidaturaService = inject(CandidaturaService);
  auth = inject(AuthService);
  toast = inject(ToastrService);

  vagaId!: string;
  vaga$!: Observable<DetalhesVaga>;
  enviando = false;
  candidatou = false;

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.vagaId = params['id'];
      this.candidatou = false;
      this.vaga$ = this.service.obterPorId(this.vagaId).pipe(
        tap({ error: (err) => console.error('Erro ao obter vaga:', err) })
      );
    });
  }

  candidatar() {
    this.enviando = true;
    this.candidaturaService.criar({ vagaId: this.vagaId }).subscribe({
      next: () => {
        this.enviando = false;
        this.candidatou = true;
        this.toast.success('Candidatura realizada com sucesso!');
      },
      error: (err) => {
        this.enviando = false;
        console.error(err);
        if (err.status === 409) {
          this.candidatou = true;
          this.toast.info('Você já se candidatou a esta vaga.');
        } else if (err.status === 403) {
          this.toast.error('Seu tipo de usuário não pode se candidatar.');
        } else {
          this.toast.error('Erro ao realizar a candidatura.');
        }
      },
    });
  }
}