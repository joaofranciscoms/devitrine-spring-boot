-- DeVitrine: estrutura logica do banco para documentacao.
-- Origem: DDL extraido do banco gerado pelo Hibernate/JPA.
-- Executar conectado ao banco devitrine, sem as tabelas abaixo existentes.
-- Este arquivo nao e uma migracao para um banco ja populado.
--
-- Adaptacoes de apresentacao:
-- * Tabelas ordenadas conforme suas dependencias.
-- * Nomes de constraints substituidos por nomes descritivos.
-- * VARCHAR e CHECK IN usados para simplificar a leitura.
-- * Omitidas as clausulas padrao MATCH SIMPLE e ON UPDATE/DELETE NO ACTION.
-- * Omitidos COLLATE default, TABLESPACE e ALTER OWNER das tabelas.
--   Proprietario e tablespace passam a seguir o usuario/configuracao da sessao.
-- * Preservados campos, tipos, nulabilidade e regras de integridade.
-- * UUIDs e datas continuam sem DEFAULT: sao fornecidos pela aplicacao.

BEGIN;

-- 1. Usuarios e classificacao tecnologica

CREATE TABLE public.usuario (
    id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(255) NOT NULL,

    CONSTRAINT pk_usuario PRIMARY KEY (id),
    CONSTRAINT uk_usuario_login UNIQUE (login),
    CONSTRAINT ck_usuario_tipo CHECK (
        tipo_usuario IN ('EMPRESA', 'PESQUISADOR', 'DESENVOLVEDOR')
    )
);

CREATE TABLE public.tecnologia (
    id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,

    CONSTRAINT pk_tecnologia PRIMARY KEY (id),
    CONSTRAINT uk_tecnologia_nome UNIQUE (nome)
);

-- 2. Conteudos publicados pelos usuarios

CREATE TABLE public.post (
    id UUID NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    data_atualizacao TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,

    CONSTRAINT pk_post PRIMARY KEY (id),
    CONSTRAINT fk_post_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id)
);

CREATE TABLE public.projeto (
    id UUID NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    link VARCHAR(255),
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    data_atualizacao TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,

    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_projeto_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id),
    CONSTRAINT ck_projeto_tipo CHECK (
        tipo IN ('PROJETO_PESSOAL', 'PROJETO_PESQUISA', 'ARTIGO_CIENTIFICO')
    )
);

CREATE TABLE public.ideia (
    id UUID NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    data_atualizacao TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,

    CONSTRAINT pk_ideia PRIMARY KEY (id),
    CONSTRAINT fk_ideia_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id)
);

CREATE TABLE public.vaga (
    id UUID NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    requisitos TEXT NOT NULL,
    -- Grafia original preservada para corresponder ao banco existente.
    numero_candidator INTEGER,
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    data_atualizacao TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,

    CONSTRAINT pk_vaga PRIMARY KEY (id),
    CONSTRAINT fk_vaga_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id)
);

-- 3. Interacoes e candidaturas

CREATE TABLE public.comentario (
    id UUID NOT NULL,
    conteudo TEXT NOT NULL,
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    data_atualizacao TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,
    -- Campo opcional no DDL original; nao foi acrescentado NOT NULL.
    post_id UUID,

    CONSTRAINT pk_comentario PRIMARY KEY (id),
    CONSTRAINT fk_comentario_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id),
    CONSTRAINT fk_comentario_post FOREIGN KEY (post_id)
        REFERENCES public.post (id)
);

CREATE TABLE public.colaboracao (
    id UUID NOT NULL,
    conteudo TEXT NOT NULL,
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    data_atualizacao TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,
    ideia_id UUID NOT NULL,

    CONSTRAINT pk_colaboracao PRIMARY KEY (id),
    CONSTRAINT fk_colaboracao_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id),
    CONSTRAINT fk_colaboracao_ideia FOREIGN KEY (ideia_id)
        REFERENCES public.ideia (id)
);

CREATE TABLE public.candidatura (
    id UUID NOT NULL,
    data_postagem TIMESTAMP(6) WITHOUT TIME ZONE,
    autor_id UUID NOT NULL,
    vaga_id UUID NOT NULL,

    CONSTRAINT pk_candidatura PRIMARY KEY (id),
    CONSTRAINT uk_candidatura_vaga_autor UNIQUE (vaga_id, autor_id),
    CONSTRAINT fk_candidatura_autor FOREIGN KEY (autor_id)
        REFERENCES public.usuario (id),
    CONSTRAINT fk_candidatura_vaga FOREIGN KEY (vaga_id)
        REFERENCES public.vaga (id)
);

-- 4. Associacao entre projetos e tecnologias

CREATE TABLE public.projeto_tecnologia (
    projeto_id UUID NOT NULL,
    tecnologia_id UUID NOT NULL,

    CONSTRAINT pk_projeto_tecnologia PRIMARY KEY (projeto_id, tecnologia_id),
    CONSTRAINT fk_projeto_tecnologia_projeto FOREIGN KEY (projeto_id)
        REFERENCES public.projeto (id),
    CONSTRAINT fk_projeto_tecnologia_tecnologia FOREIGN KEY (tecnologia_id)
        REFERENCES public.tecnologia (id)
);

COMMIT;
