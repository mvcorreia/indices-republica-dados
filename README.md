# Projeto Índices 2.0 — Java + SQLite

Atualização didática do sistema da atividade **República dos Dados**. O aluno
continua digitando resultados já calculados; o Java não faz percentuais nem
médias. A versão 2.0 preserva cada análise em um arquivo SQLite e permite
consultar o histórico depois de fechar e abrir o programa.

## O que mudou na versão 2.0

- banco local `dados/indices.db`, criado automaticamente;
- opção para salvar uma nova análise sem apagar as anteriores;
- listagem do histórico;
- recuperação do último registro;
- classe DAO para separar o SQL do menu;
- validação de campos obrigatórios;
- transação, `PreparedStatement` e tratamento de erros;
- driver Xerial SQLite JDBC 3.54.1.0 já incluído em `lib`;
- scripts simples para Windows, Linux e macOS.

## Dados da demonstração

- Total de registros válidos: 316
- Candidato: Carlos Chave Primária
- Total do candidato: 83
- Percentual do candidato: 26,27%
- Satisfação média: 5,60 de 10
- Tema Saúde: 68 registros
- Percentual de Saúde: 21,52%

Os valores são apenas digitados e exibidos. As contas continuam sendo feitas
antes da execução, na planilha ou na investigação da turma.

## Executar no Windows

1. Instale um JDK 11 ou superior.
2. Extraia o ZIP inteiro.
3. Dê dois cliques em `executar.bat`.

## Executar no Linux ou macOS

```text
chmod +x executar.sh
./executar.sh
```

## Executar manualmente

Windows:

```text
javac -encoding UTF-8 -cp "lib/*" -d out src\*.java
java -cp "out;lib/*" SistemaIndices
```

Linux/macOS:

```text
javac -encoding UTF-8 -cp "lib/*" -d out src/*.java
java -cp "out:lib/*" SistemaIndices
```

## Demonstração de cinco minutos

1. Use a opção 6 para carregar o exemplo.
2. Use a opção 5 para mostrar a conclusão.
3. Use a opção 7 para salvar no SQLite.
4. Feche o programa com 0.
5. Abra novamente e use a opção 8.
6. Observe que o registro continuou armazenado.

## Estrutura

- `src/ResultadoIndices.java`: objeto com os índices;
- `src/ConexaoSQLite.java`: conexão e criação da tabela;
- `src/ResultadoIndicesDAO.java`: salvar e consultar;
- `src/SistemaIndices.java`: menu versão 2.0;
- `database/schema.sql`: documentação versionável da estrutura;
- `dados/`: banco local criado durante a execução;
- `lib/`: driver SQLite já incluído;
- `docs/`: aula, desafio, testes e roteiro do GitHub Web.

As fontes técnicas e os links oficiais utilizados estão em
`docs/REFERENCIAS_TECNICAS.md`.

## Qualidade e privacidade

O banco gerado está no `.gitignore`. Publique o código e o `schema.sql`, não o
arquivo `indices.db` quando ele tiver dados reais. Para uma atividade fictícia,
o professor pode decidir anexar uma cópia sem informações pessoais.
