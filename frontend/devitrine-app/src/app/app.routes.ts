import { Routes } from '@angular/router';
import { Template } from './template/template';
import { ListagemPosts } from './posts/listagem-posts/listagem-posts';
import { ConteudoPost } from './posts/conteudo-post/conteudo-post';
import { ComentarioPost } from './comentarios/comentario-post/comentario-post';
import { Login } from './auth/login/login';
import { authGuard } from './auth/auth-guard';
import { CadastroPost } from './posts/cadastro-post/cadastro-post';
import { ListagemVagas } from './vagas/listagem-vagas/listagem-vagas';
import { ConteudoVaga } from './vagas/conteudo-vaga/conteudo-vaga';
import { CadastroVaga } from './vagas/cadastro-vaga/cadastro-vaga';
import { ListagemProjetos } from './projetos/listagem-projetos/listagem-projetos';
import { ConteudoProjeto } from './projetos/conteudo-projeto/conteudo-projeto';
import { CadastroProjeto } from './projetos/cadastro-projeto/cadastro-projeto';
import { ListagemIdeias } from './ideias/listagem-ideias/listagem-ideias';
import { ConteudoIdeia } from './ideias/conteudo-ideia/conteudo-ideia';
import { CadastroIdeia } from './ideias/cadastro-ideia/cadastro-ideia';
import { ColaboracaoIdeia } from './colaboracoes/colaboracao-ideia/colaboracao-ideia';

export const routes: Routes = [
    {
        path: 'login',
        component: Login,
    },
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        
        path: 'paginas',
        component: Template,
        canActivate: [authGuard],
        children: [
            {
                path: 'listagem-posts',
                component: ListagemPosts
            },
            {
                path: 'conteudo-post',
                component: ConteudoPost
            },
            {
                path: 'comentario-post',
                component: ComentarioPost
            },
            {
                path: 'cadastro-post',
                component: CadastroPost
            },
            {
                path: 'listagem-vagas',
                component: ListagemVagas
            },
            {
                path: 'conteudo-vaga',
                component: ConteudoVaga
            },
            {
                path: 'cadastro-vaga',
                component: CadastroVaga
            },
            {
                path: 'listagem-projetos',
                component: ListagemProjetos
            },
            {
                path: 'conteudo-projeto',
                component: ConteudoProjeto
            },
            {
                path: 'cadastro-projeto',
                component: CadastroProjeto
            },
            {
                path: 'listagem-ideias',
                component: ListagemIdeias
            },
            {
                path: 'conteudo-ideia',
                component: ConteudoIdeia
            },
            {
                path: 'cadastro-ideia',
                component: CadastroIdeia
            },
            {
                path: 'colaboracao-ideia',
                component: ColaboracaoIdeia
            }
        ]
    }
];
