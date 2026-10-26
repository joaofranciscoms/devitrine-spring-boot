import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PostService } from '../post-service';
import { DadosPostForm } from '../dados-post';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterLink } from '@angular/router';
import { Header } from '../../common/components/header/header';

interface SalvarPostForm {
  titulo: FormControl<string>;
  conteudo: FormControl<string>;
}

@Component({
  selector: 'app-cadastro-post',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './cadastro-post.html',
  styleUrl: './cadastro-post.scss',
})
export class CadastroPost implements OnInit {
  form!: FormGroup<SalvarPostForm>;
  service = inject(PostService);
  toast = inject(ToastrService);
  router = inject(Router);

  ngOnInit(): void {
    this.form = new FormGroup<SalvarPostForm>({
      titulo: new FormControl('', { nonNullable: true, validators: Validators.required }),
      conteudo: new FormControl('', { nonNullable: true, validators: Validators.required }),
    });
  }

  handleSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const dados: DadosPostForm = this.form.getRawValue();

    this.service.criar(dados).subscribe({
      next: (post) => {
        this.toast.success('Post criado com sucesso!');
        this.router.navigate(['paginas/conteudo-post'], {
          queryParams: { id: post.id },
        });
      },
      error: () => this.toast.error('Erro ao criar o post!'),
    });
  }
}