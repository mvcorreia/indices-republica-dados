# Checklist de testes — versão 2.0

| ID | Preparação | Ação | Resultado esperado |
| --- | --- | --- | --- |
| T01 | Banco ainda vazio | Abrir o programa | Tabela é criada e o menu mostra v2.0.0. |
| T02 | Campos vazios | Escolher salvar | Sistema recusa e orienta preencher os dados. |
| T03 | Exemplo carregado | Salvar | Mensagem informa sucesso e um ID. |
| T04 | Um registro salvo | Fechar e abrir; listar histórico | Registro continua disponível. |
| T05 | Um registro salvo | Salvar novamente | Novo ID é criado; registro anterior permanece. |
| T06 | Dois registros salvos | Listar histórico | Os dois aparecem, começando pelo mais recente. |
| T07 | Histórico existente | Carregar último | Os dados mais recentes aparecem no resultado atual. |
| T08 | Digitação atual preenchida | Iniciar nova análise | A tela atual é limpa, mas o histórico permanece. |
| T09 | Campo numérico | Digitar letras | Sistema pede um número inteiro novamente. |
| T10 | Campo textual | Deixar vazio | Sistema não aceita o campo em branco. |

## Registro de execução

| Teste | Passou? | Evidência | Defeito encontrado | Responsável |
| --- | --- | --- | --- | --- |
| T01 | Sim | "Banco preparado em: ...\dados\indices.db" e título "v2.0.0" | Nenhum | Marcus |
| T02 | Sim | "Salvamento cancelado: preencha todos os dados primeiro." | Nenhum | Marcus |
| T03 | Sim | "Dados preservados com sucesso. Registro criado: #1" | Nenhum | Marcus |
| T04 | Sim | Após fechar (0) e reabrir, opção 8 mostrou "Registro #1" | Nenhum | Marcus |
| T05 | Sim | "Registro criado: #2"; #1 continuou no histórico | Nenhum | Marcus |
| T06 | Sim | Opção 8 listou #2 e depois #1 (mais recente primeiro) | Nenhum | Marcus |
| T07 | Sim | Opção 9: "Registro #2 recuperado." com data/hora | Nenhum | Marcus |
| T08 | Sim | Opção 10 + opção 5: "dados ainda estão incompletos"; opção 8 ainda lista #1 e #2 | Nenhum | Marcus |
| T09 | Sim | Digitei "abc" na quantidade: "Digite um número inteiro." | Nenhum | Marcus |
| T10 | Sim | Nome vazio: "Este campo não pode ficar vazio." | Nenhum | Marcus |

## Extensão opcional — buscar registro por ID (opção 11)

**Critério de aceitação:** dado um ID informado pelo usuário, o sistema exibe o
registro correspondente se ele existir; caso contrário, informa que não há
registro com esse ID. Entradas não numéricas não são aceitas. Nenhum índice é
calculado e o histórico não é alterado.

| ID | Preparação | Ação | Resultado esperado |
| --- | --- | --- | --- |
| T11 | Registro #1 salvo | Opção 11, digitar `1` | Registro #1 aparece com data/hora e conclusão. |
| T12 | Banco sem o ID 999 | Opção 11, digitar `999` | Mensagem "Nenhum registro com o ID #999." |
| T13 | Qualquer | Opção 11, digitar `abc` | Sistema pede um número inteiro novamente. |

## Registro de execução da extensão

| Teste | Passou? | Evidência | Defeito encontrado | Responsável |
| --- | --- | --- | --- | --- |
| T11 | Sim | Opção 11, ID 1: "Registro #1 encontrado." | Nenhum | Marcus |
| T12 | Sim | Opção 11, ID 999: "Nenhum registro com o ID #999." | Nenhum | Marcus |
| T13 | Sim | Opção 11, "abc": "Digite um número inteiro." | Nenhum | Marcus |
