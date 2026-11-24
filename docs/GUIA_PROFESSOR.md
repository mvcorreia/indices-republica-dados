# Guia do professor — aula definitiva da versão 2.0

## Objetivo

Mostrar que qualidade não termina quando o menu funciona. Um requisito novo —
preservar dados — muda arquitetura, testes, documentação, configuração e versão.

## Duração sugerida: 100 minutos

| Tempo | Etapa | Ação |
| --- | --- | --- |
| 0–10 min | Problema | Execute a versão 1.2, feche e pergunte: “onde foram parar os dados?” |
| 10–25 min | Conceito | Explique memória, persistência, SQLite, JDBC e DAO. |
| 25–40 min | Código | Percorra as quatro classes, sem explicar linha por linha. |
| 40–55 min | Demonstração | Carregue o exemplo, salve, feche, reabra e liste. |
| 55–75 min | Desafio | Duplas executam T01 a T10 e coletam evidências. |
| 75–90 min | GitHub Web | Envie arquivos, mostre commits e crie a Release v2.0.0. |
| 90–100 min | Fechamento | Relacione requisito, qualidade, teste, commit e versão. |

## Fala simples para os conceitos

**Memória:** “O objeto existe enquanto o programa está rodando.”

**Persistência:** “Salvar é fazer o dado sobreviver ao encerramento.”

**SQLite:** “É um banco em arquivo. Não precisamos instalar um servidor.”

**JDBC:** “É a ponte usada pelo Java para conversar com o banco.”

**DAO:** “É a classe que sabe SQL; o menu só sabe pedir para salvar ou buscar.”

**Transação:** “Ou o registro entra inteiro, ou a operação é desfeita.”

**Rastreabilidade:** “Conseguimos ligar a necessidade ao código, ao teste, ao
commit e à Release.”

## Sequência da demonstração

1. Abra `executar.bat` ou `executar.sh`.
2. Mostre `v2.0.0` no título do menu.
3. Escolha 6, 5 e 7.
4. Saia com 0.
5. Abra outra vez e escolha 8.
6. Salve uma segunda vez e confirme dois IDs.
7. Escolha 9 e mostre o último registro.
8. Tente salvar uma análise vazia e observe a validação.

## Perguntas para discussão

- O banco deve sobrescrever ou criar histórico? Por quê?
- Quais defeitos podem ocorrer durante um salvamento?
- Por que separar SQL do menu melhora a manutenção?
- Qual arquivo deve entrar no Git: `schema.sql` ou `indices.db`?
- Que evidência prova que o requisito foi atendido?

## Resposta esperada do desafio

A solução de referência já está implementada. A avaliação deve observar se a
dupla compreendeu a preservação, executou os testes, registrou evidências e
explicou a rastreabilidade. Não é necessário exigir SQL avançado.

## Avaliação sugerida — 10 pontos

| Critério | Pontos |
| --- | ---: |
| Demonstração da persistência | 2,0 |
| Checklist com evidências | 2,0 |
| Explicação de SQLite, JDBC e DAO | 2,0 |
| Commits e Release no GitHub Web | 2,0 |
| Relação com atributos de qualidade | 2,0 |
