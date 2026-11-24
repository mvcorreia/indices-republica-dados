# Histórico de mudanças

## 2.0.0 — persistência com SQLite

### Adicionado

- armazenamento local em SQLite;
- histórico de análises;
- recuperação do último registro;
- classes `ConexaoSQLite` e `ResultadoIndicesDAO`;
- validação antes do salvamento;
- scripts de execução com o driver incluído;
- documentação de testes, desafio e GitHub Web.

### Mantido

- entrada manual de dados;
- ausência de cálculos no Java;
- modelo simples de orientação a objetos;
- conclusão textual da atividade.

### Observação

O arquivo `indices.db` é local e está ignorado pelo Git para evitar a publicação
acidental de registros.

## 2.1.0 — extensão opcional (não publicada como Release)

### Adicionado

- opção 11 do menu: buscar registro por ID;
- método `buscarPorId` no DAO usando `PreparedStatement`;
- testes T11 a T13 no checklist.
