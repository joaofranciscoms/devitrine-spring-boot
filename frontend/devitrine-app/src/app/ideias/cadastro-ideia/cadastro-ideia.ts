import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { IdeiaService } from '../ideia-service';
import { DadosIdeiaForm } from '../dados-ideia';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterLink } from '@angular/router';
import { Header } from '../../common/components/header/header';

interface SalvarIdeiaForm {
  titulo: FormControl<string>;
  descricao: FormControl<string>;
}

@Component({
  selector: 'app-cadastro-ideia',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './cadastro-ideia.html',
  styleUrl: './cadastro-ideia.scss',
})
export class CadastroIdeia implements OnInit {
  form!: FormGroup<SalvarIdeiaForm>;
  service = inject(IdeiaService);
  toast = inject(ToastrService);
  router = inject(Router);

  ngOnInit(): void {
    this.form = new FormGroup<SalvarIdeiaForm>({
      titulo: new FormControl('', { nonNullable: true, validators: Validators.required }),
      descricao: new FormControl('', { nonNullable: true, validators: Validators.required }),
    });
  }

  handleSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const dados: DadosIdeiaForm = this.form.getRawValue();

    this.service.criar(dados).subscribe({
      next: (ideia) => {
        this.toast.success('Ideia compartilhada com sucesso!');
        this.router.navigate(['paginas/conteudo-ideia'], {
          queryParams: { id: ideia.id },
        });
      },
      error: () => this.toast.error('Erro ao compartilhar a ideia!'),
    });
  }
}
