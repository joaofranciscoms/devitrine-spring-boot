import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ProjetoService } from '../projeto-service';
import { DadosProjetoForm, TipoProjeto, TIPOS_PROJETO } from '../dados-projeto';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterLink } from '@angular/router';
import { Header } from '../../common/components/header/header';

interface SalvarProjetoForm {
  titulo: FormControl<string>;
  descricao: FormControl<string>;
  tipo: FormControl<string>;
  link: FormControl<string>;
  tecnologias: FormControl<string>;
}

@Component({
  selector: 'app-cadastro-projeto',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './cadastro-projeto.html',
  styleUrl: './cadastro-projeto.scss',
})
export class CadastroProjeto implements OnInit {
  form!: FormGroup<SalvarProjetoForm>;
  service = inject(ProjetoService);
  toast = inject(ToastrService);
  router = inject(Router);
  tipos = TIPOS_PROJETO;

  ngOnInit(): void {
    this.form = new FormGroup<SalvarProjetoForm>({
      titulo: new FormControl('', { nonNullable: true, validators: Validators.required }),
      descricao: new FormControl('', { nonNullable: true, validators: Validators.required }),
      tipo: new FormControl('', { nonNullable: true, validators: Validators.required }),
      link: new FormControl('', { nonNullable: true }),
      tecnologias: new FormControl('', { nonNullable: true, validators: Validators.required }),
    });
  }

  handleSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const valores = this.form.getRawValue();
    const dados: DadosProjetoForm = {
      titulo: valores.titulo,
      descricao: valores.descricao,
      tipo: valores.tipo as TipoProjeto,
      link: valores.link,
      tecnologias: valores.tecnologias.split(',').map(t => t.trim()).filter(t => t.length > 0),
    };

    this.service.criar(dados).subscribe({
      next: (projeto) => {
        this.toast.success('Projeto publicado com sucesso!');
        this.router.navigate(['paginas/conteudo-projeto'], {
          queryParams: { id: projeto.id },
        });
      },
      error: () => this.toast.error('Erro ao publicar o projeto!'),
    });
  }
}
