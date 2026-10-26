import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { VagaService } from '../vaga-service';
import { DadosVagaForm } from '../dados-vaga';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterLink } from '@angular/router';
import { Header } from '../../common/components/header/header';

interface SalvarVagaForm {
  titulo: FormControl<string>;
  conteudo: FormControl<string>;
  requisitos: FormControl<string>;
}

@Component({
  selector: 'app-cadastro-vaga',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './cadastro-vaga.html',
  styleUrl: './cadastro-vaga.scss',
})
export class CadastroVaga implements OnInit {
  form!: FormGroup<SalvarVagaForm>;
  service = inject(VagaService);
  toast = inject(ToastrService);
  router = inject(Router);

  ngOnInit(): void {
    this.form = new FormGroup<SalvarVagaForm>({
      titulo: new FormControl('', { nonNullable: true, validators: Validators.required }),
      conteudo: new FormControl('', { nonNullable: true, validators: Validators.required }),
      requisitos: new FormControl('', { nonNullable: true, validators: Validators.required }),
    });
  }

  handleSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const dados: DadosVagaForm = this.form.getRawValue();

    this.service.criar(dados).subscribe({
      next: (vaga) => {
        this.toast.success('Vaga criada com sucesso!');
        this.router.navigate(['paginas/conteudo-vaga'], {
          queryParams: { id: vaga.id },
        });
      },
      error: () => this.toast.error('Erro ao criar a vaga!'),
    });
  }
}