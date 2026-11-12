import type { TipoUsuario } from '../auth/login/login';

export type TipoProjeto = 'PROJETO_PESSOAL' | 'PROJETO_PESQUISA' | 'ARTIGO_CIENTIFICO';

export const TIPOS_PROJETO: { valor: TipoProjeto; descricao: string; perfilAutor: TipoUsuario }[] = [
    { valor: 'PROJETO_PESSOAL', descricao: 'Projeto Pessoal', perfilAutor: 'DESENVOLVEDOR' },
    { valor: 'PROJETO_PESQUISA', descricao: 'Projeto de Pesquisa', perfilAutor: 'PESQUISADOR' },
    { valor: 'ARTIGO_CIENTIFICO', descricao: 'Artigo Científico', perfilAutor: 'PESQUISADOR' },
];

export function tiposProjetoPermitidos(perfil: TipoUsuario | null) {
    return TIPOS_PROJETO.filter(tipo => tipo.perfilAutor === perfil);
}

export function descricaoTipoProjeto(tipo: TipoProjeto): string {
    return TIPOS_PROJETO.find(t => t.valor === tipo)?.descricao ?? tipo;
}

export class DadosProjetoForm {
    titulo!: string;
    descricao!: string;
    tipo!: TipoProjeto;
    link!: string;
    tecnologias!: string[];
}

export class DetalhesProjeto {
    id!: string;
    titulo!: string;
    descricao!: string;
    tipo!: TipoProjeto;
    link!: string;
    tecnologias!: string[];
    dataPostagem!: Date;
    dataAtualizacao!: Date;
    autorNome!: string;
}
