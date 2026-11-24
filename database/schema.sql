-- Estrutura criada automaticamente por ConexaoSQLite.java.
-- Este arquivo serve como documentação versionável do banco.

CREATE TABLE IF NOT EXISTS resultados (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    total_validos INTEGER NOT NULL,
    nome_candidato TEXT NOT NULL,
    total_candidato INTEGER NOT NULL,
    percentual_candidato TEXT NOT NULL,
    satisfacao_media TEXT NOT NULL,
    total_saude INTEGER NOT NULL,
    percentual_saude TEXT NOT NULL,
    conclusao TEXT NOT NULL,
    criado_em TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
