import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ColaboracaoService } from '../colaboracao-service';
import { DadosColaboracaoForm } from '../dados-colaboracao';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Header } from '../../common/components/header/header';

interface SalvarColaboracaoForm {
  conteudo: FormControl<string>;
}

@Component({
  selector: 'app-colaboracao-ideia',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './colaboracao-ideia.html',
  styleUrl: './colaboracao-ideia.scss',
})
export class ColaboracaoIdeia implements OnInit {
  form!: FormGroup<SalvarColaboracaoForm>;
  service = inject(ColaboracaoService);
  toast = inject(ToastrService)
  router = inject(Router)
  route = inject(ActivatedRoute)
  idIdeia = this.route.snapshot.queryParamMap.get('id')!;

  ngOnInit(): void {
    this.form = new FormGroup<SalvarColaboracaoForm>({
      conteudo: new FormControl('', { nonNullable: true, validators: Validators.required })
    });
  }

  handleSubmit(){
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const dados: DadosColaboracaoForm = {
        conteudo: this.form.getRawValue().conteudo,
        idIdeia: this.idIdeia,
    };

    this.service.criar(dados).subscribe({
        next: () => {
            this.toast.success('Colaboração enviada com sucesso!');
            this.router.navigate(['paginas/conteudo-ideia'], {
                queryParams: { id: this.idIdeia }
            });
        },
        error: () => this.toast.error('Erro ao enviar a colaboração!')
    });
  }
}
