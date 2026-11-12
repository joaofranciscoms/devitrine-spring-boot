-- DeVitrine: criacao do banco de dados.
-- Origem: DDL extraido do PostgreSQL pelo autor do projeto.
-- Executar separadamente, conectado a outro banco, por exemplo postgres.
-- CREATE DATABASE nao pode ser executado dentro de uma transacao.
-- O banco devitrine ainda nao deve existir.
-- A localidade abaixo foi preservada do ambiente original (Windows).
-- Em outro servidor, verificar a disponibilidade e compatibilidade da localidade.

CREATE DATABASE devitrine
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = FALSE;

-- Depois da criacao, abrir uma conexao ao banco devitrine e executar
-- 02_estrutura_tabelas.sql nessa nova conexao.
