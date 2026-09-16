import { Routes } from '@angular/router';
import { Template } from './template/template';
import { ListagemPosts } from './posts/listagem-posts/listagem-posts';
import { ConteudoPost } from './posts/conteudo-post/conteudo-post';

export const routes: Routes = [
    {
        path: 'paginas',
        component: Template,
        children: [
            {
                path: 'listagem-posts',
                component: ListagemPosts
            },
            {
                path: "conteudo-post",
                component: ConteudoPost
            }
        ]
    }
];
