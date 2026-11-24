# GitHub Web — atualização para a versão 2.0

O roteiro usa somente o navegador. Não é necessário instalar Git nem usar o
terminal para publicar o projeto.

## 1. Fazer uma cópia de segurança da versão anterior

Antes de enviar a atualização, confirme que a Release `v1.2.0` existe. Ela será
a referência da versão sem persistência.

## 2. Abrir o repositório

1. Entre em `github.com`.
2. Abra o repositório `indices-republica-dados`.
3. Confira se você está na branch `main`.

Se ainda não houver repositório, clique no `+`, escolha **New repository**, use
o nome acima e marque **Add a README file**.

## 3. Enviar os arquivos da versão 2.0

1. Clique em **Add file**.
2. Escolha **Upload files**.
3. Arraste as pastas `src`, `database`, `docs`, `lib` e os arquivos da raiz.
4. Não envie `dados/indices.db`.
5. Mensagem do commit: `feat: adiciona persistencia SQLite`.
6. Clique em **Commit changes**.

## 4. Registrar a documentação em outro commit

1. Abra `CHANGELOG.md`.
2. Clique no ícone de lápis.
3. Confira a seção 2.0.0 e faça uma pequena correção, se necessário.
4. Mensagem: `docs: registra testes e mudancas da versao 2.0`.
5. Clique em **Commit changes**.

## 5. Mostrar a rastreabilidade

1. Clique em **Commits** na página principal.
2. Abra o commit da persistência.
3. Mostre os arquivos alterados e a mensagem.
4. Explique: requisito → código → teste → commit → versão.

## 6. Criar a Release v2.0.0

1. Abra **Releases**.
2. Clique em **Draft a new release**.
3. Em **Choose a tag**, digite `v2.0.0`.
4. Escolha **Create new tag: v2.0.0 on publish**.
5. Título: `Versão 2.0.0 — persistência com SQLite`.
6. Cole o conteúdo de `docs/NOTAS_RELEASE_v2.0.0.md`.
7. Clique em **Publish release**.

## 7. Evidências da equipe

- URL do repositório;
- tela dos commits;
- tela da Release `v2.0.0`;
- execução da opção 7 salvando;
- nova execução da opção 8 recuperando o histórico;
- checklist de testes preenchido.

## Mensagens de commit sugeridas

| Objetivo | Mensagem |
| --- | --- |
| Persistência | `feat: adiciona persistencia SQLite` |
| Histórico | `feat: lista e recupera registros salvos` |
| Correção | `fix: impede salvar dados incompletos` |
| Testes e aula | `docs: adiciona criterios e evidencias da versao 2.0` |

## Cuidado com os dados

O arquivo `.gitignore` impede o envio normal de `dados/indices.db`. Como o
upload pelo navegador pode ignorar essa intenção se o arquivo for arrastado
manualmente, verifique a lista antes de confirmar. Publique o `schema.sql`, não
o banco preenchido.
