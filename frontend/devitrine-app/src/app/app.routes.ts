import { Routes } from '@angular/router';
import { Template } from './template/template';
import { ListagemPosts } from './posts/listagem-posts/listagem-posts';
import { ConteudoPost } from './posts/conteudo-post/conteudo-post';
import { ComentarioPost } from './comentarios/comentario-post/comentario-post';
import { Login } from './auth/login/login';
import { authGuard } from './auth/auth-guard';

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
            }
        ]
    }
];
