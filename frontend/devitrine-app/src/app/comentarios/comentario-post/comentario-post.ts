import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ComentarioService } from '../comentario-service';
import { DadosComentarioForm, DetalhesComentario } from '../dados-comentario';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

interface SalvarComentarioForm {
  conteudo: FormControl<string>;
}

@Component({
  selector: 'app-comentario-post',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './comentario-post.html',
  styleUrl: './comentario-post.scss',
})

export class ComentarioPost implements OnInit {
  form!: FormGroup<SalvarComentarioForm>;
  service = inject(ComentarioService);
  toast = inject(ToastrService)
  router = inject(Router)
  route = inject(ActivatedRoute)
  idPost = this.route.snapshot.queryParamMap.get('id')!;

  ngOnInit(): void {
     this.form = new FormGroup<SalvarComentarioForm>({
      conteudo: new FormControl('', { nonNullable: true, validators: Validators.required })
    });
  }

  handleSubmit(){
    const dados: DadosComentarioForm = {
        conteudo: this.form.getRawValue().conteudo,
        idPost: this.idPost,
    };

    this.service.criar(dados).subscribe({
        next: () => {
            this.toast.success('Comentário realizado com sucesso!');
            this.router.navigate(['paginas/conteudo-post'], {
                queryParams: { id: this.idPost }
            });
        },
        error: () => this.toast.error('Erro!')
    });
  }
}
