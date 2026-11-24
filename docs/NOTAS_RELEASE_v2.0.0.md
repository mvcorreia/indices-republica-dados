# Versão 2.0.0 — persistência com SQLite

## Novidades

- preservação local das análises em SQLite;
- histórico sem sobrescrever registros anteriores;
- recuperação do último registro salvo;
- identificação por ID e data/hora;
- menu atualizado com a versão 2.0.0;
- driver JDBC incluído no projeto.

## Qualidade

- validação antes de salvar;
- transação com confirmação e desfazimento em caso de falha;
- `PreparedStatement` para os comandos de inserção;
- DAO separando o banco da interface;
- tratamento de erros e mensagens para o usuário;
- checklist com critérios de aceitação;
- `.gitignore` para não publicar o banco local.

## Compatibilidade

- Java 11 ou superior;
- Windows, Linux e macOS suportados pelas bibliotecas nativas incluídas.

## Observação didática

O programa não calcula percentuais ou médias. Ele recebe, exibe e preserva os
resultados já obtidos pela equipe na análise da base.
