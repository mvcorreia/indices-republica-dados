# Desafio: o dado precisa sobreviver

## Situação-problema

Na versão 1.2, os resultados existiam apenas enquanto o programa estava aberto.
Ao encerrar o menu, o objeto era perdido. A equipe recebeu uma nova necessidade:

> Preservar cada conjunto de índices, manter o histórico e recuperar o último
> registro sem transformar o programa em um sistema complexo.

## Critérios de aceitação

1. O Java continua sem calcular índices.
2. Um registro completo pode ser salvo.
3. Fechar o programa não apaga o registro.
4. Salvar novamente cria uma nova linha, sem sobrescrever a anterior.
5. O histórico apresenta ID e data/hora.
6. O último registro pode ser recuperado.
7. Dados incompletos não são salvos.
8. O banco local não é publicado por engano no GitHub.

## Solução implementada

- SQLite: banco simples em um arquivo local;
- JDBC: conexão do Java com o banco;
- DAO: classe que concentra o SQL;
- `PreparedStatement`: parâmetros separados do comando;
- transação: `commit` confirma e `rollback` desfaz uma falha;
- `CREATE TABLE IF NOT EXISTS`: inicialização repetível;
- `.gitignore`: protege o banco local;
- histórico: cada salvamento usa `INSERT` e recebe novo ID.

## Relação com Gestão e Qualidade de Software

| Conceito | Evidência no projeto |
| --- | --- |
| Adequação funcional | Salvar, listar e recuperar atendem à necessidade proposta. |
| Confiabilidade | Transação evita registro parcialmente gravado. |
| Usabilidade | Menu mostra ações e mensagens claras. |
| Manutenibilidade | DAO separa persistência da interface. |
| Segurança e privacidade | Banco local é ignorado pelo Git. |
| Testabilidade | Critérios de aceitação viram casos de teste. |
| Rastreabilidade | `CHANGELOG`, commits, versão e Release documentam a evolução. |
| Gestão de configuração | Código, esquema e documentação são itens versionados. |

## Desafio para os alunos

Em duplas, executem a versão 1.2, fechem o programa e descrevam o defeito. Depois
executem a 2.0 e produzam evidências para os oito critérios de aceitação. Cada
dupla deve entregar: tabela de testes, duas capturas do menu, link do repositório,
lista de commits e Release `v2.0.0`.

## Extensão opcional

Sem alterar as fórmulas, acrescente uma opção que busque um registro por ID.
Antes de programar, escreva o critério de aceitação e três testes: ID existente,
ID inexistente e entrada não numérica.
