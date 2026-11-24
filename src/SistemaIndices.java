import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Sistema Índices 2.0
 *
 * Recebe resultados já calculados, apresenta uma conclusão e preserva cada
 * análise em um banco SQLite. Não há cálculo de índices neste programa.
 */
public class SistemaIndices {
    private static final String VERSAO = "2.0.0";
    private static final Scanner TECLADO = new Scanner(System.in);
    private static final ResultadoIndicesDAO DAO = new ResultadoIndicesDAO();
    private static ResultadoIndices resultadoAtual = new ResultadoIndices();

    /** Método principal: prepara o banco e mantém o menu funcionando. */
    public static void main(String[] args) {
        try {
            ConexaoSQLite.inicializarBanco();
            System.out.println("Banco preparado em: " + ConexaoSQLite.getCaminhoBanco());
        } catch (SQLException erro) {
            System.out.println("Não foi possível iniciar o banco de dados.");
            System.out.println("Detalhe: " + erro.getMessage());
            System.out.println("Confira se os arquivos da pasta lib estão no classpath.");
            return;
        }

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            // Switch tradicional: compatível com Java 11 ou superior.
            switch (opcao) {
                case 1:
                    informarTotalValido();
                    break;
                case 2:
                    informarCandidato();
                    break;
                case 3:
                    informarSatisfacao();
                    break;
                case 4:
                    informarSaude();
                    break;
                case 5:
                    mostrarResultado(resultadoAtual);
                    break;
                case 6:
                    carregarExemploDaAula();
                    break;
                case 7:
                    salvarNoBanco();
                    break;
                case 8:
                    listarHistorico();
                    break;
                case 9:
                    carregarUltimoRegistro();
                    break;
                case 10:
                    iniciarNovaAnalise();
                    break;
                case 11:
                    buscarRegistroPorId();
                    break;
                case 0:
                    System.out.println("Programa encerrado. Os registros salvos foram preservados.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        TECLADO.close();
    }

    /** Exibe as opções e deixa a versão visível para a turma. */
    private static void mostrarMenu() {
        System.out.println("\n=== ÍNDICES DA REPÚBLICA DOS DADOS | v" + VERSAO + " ===");
        System.out.println("1  - Informar total de registros válidos");
        System.out.println("2  - Informar candidato e quantidade");
        System.out.println("3  - Informar satisfação média");
        System.out.println("4  - Informar quantidade do tema Saúde");
        System.out.println("5  - Mostrar dados atuais e conclusão");
        System.out.println("6  - Carregar exemplo da aula");
        System.out.println("7  - Salvar dados atuais no SQLite");
        System.out.println("8  - Listar histórico salvo");
        System.out.println("9  - Carregar o último registro salvo");
        System.out.println("10 - Iniciar nova análise vazia");
        System.out.println("11 - Buscar registro por ID");
        System.out.println("0  - Sair");
    }

    private static void informarTotalValido() {
        int total = lerInteiroNaoNegativo("Total de registros válidos: ");
        resultadoAtual.setTotalValidos(total);
        System.out.println("Total registrado na memória. Use a opção 7 para salvar.");
    }

    private static void informarCandidato() {
        String nome = lerTextoObrigatorio("Nome do candidato: ");
        int quantidade = lerInteiroNaoNegativo("Quantidade encontrada: ");
        String percentual = lerTextoObrigatorio("Percentual já calculado: ");

        resultadoAtual.informarCandidato(nome, quantidade, percentual);
        System.out.println("Candidato registrado na memória. Use a opção 7 para salvar.");
    }

    private static void informarSatisfacao() {
        String media = lerTextoObrigatorio("Satisfação média já calculada: ");
        resultadoAtual.setSatisfacaoMedia(media);
        System.out.println("Satisfação registrada na memória. Use a opção 7 para salvar.");
    }

    private static void informarSaude() {
        int quantidade = lerInteiroNaoNegativo("Quantidade do tema Saúde: ");
        String percentual = lerTextoObrigatorio("Percentual de Saúde já calculado: ");

        resultadoAtual.setTotalSaude(quantidade);
        resultadoAtual.setPercentualSaude(percentual);
        System.out.println("Tema Saúde registrado na memória. Use a opção 7 para salvar.");
    }

    /** Exibe um objeto e sua conclusão sem fazer nenhum cálculo. */
    private static void mostrarResultado(ResultadoIndices resultado) {
        if (!resultado.estaCompleto()) {
            System.out.println("Os dados ainda estão incompletos.");
            System.out.println("Preencha as opções 1 a 4 ou carregue o exemplo da opção 6.");
            return;
        }

        System.out.println("\n=== RESULTADO ATUAL ===");
        if (resultado.getId() > 0) {
            System.out.println("Registro do banco: #" + resultado.getId()
                + " | salvo em " + resultado.getCriadoEm());
        }
        System.out.println(resultado.gerarConclusao());
    }

    /** Preenche o objeto com os dados usados na demonstração da aula. */
    private static void carregarExemploDaAula() {
        resultadoAtual = new ResultadoIndices();
        resultadoAtual.setTotalValidos(316);
        resultadoAtual.informarCandidato("Carlos Chave Primária", 83, "26,27%");
        resultadoAtual.setSatisfacaoMedia("5,60");
        resultadoAtual.setTotalSaude(68);
        resultadoAtual.setPercentualSaude("21,52%");
        System.out.println("Exemplo carregado na memória.");
        System.out.println("Use 5 para visualizar e 7 para preservar no banco.");
    }

    /** Valida os campos e insere uma nova linha no banco. */
    private static void salvarNoBanco() {
        if (!resultadoAtual.estaCompleto()) {
            System.out.println("Salvamento cancelado: preencha todos os dados primeiro.");
            return;
        }

        try {
            long id = DAO.salvar(resultadoAtual);
            System.out.println("Dados preservados com sucesso. Registro criado: #" + id);
            System.out.println("O histórico anterior não foi apagado.");
        } catch (SQLException erro) {
            System.out.println("Falha ao salvar os dados.");
            System.out.println("Detalhe: " + erro.getMessage());
        }
    }

    /** Consulta e exibe todas as versões de dados gravadas localmente. */
    private static void listarHistorico() {
        try {
            List<ResultadoIndices> historico = DAO.listarTodos();

            if (historico.isEmpty()) {
                System.out.println("Nenhum registro foi salvo ainda.");
                return;
            }

            System.out.println("\n=== HISTÓRICO PRESERVADO ===");
            for (ResultadoIndices item : historico) {
                System.out.println("----------------------------------------");
                System.out.println("Registro #" + item.getId() + " | " + item.getCriadoEm());
                System.out.println(item.gerarConclusao());
            }
        } catch (SQLException erro) {
            System.out.println("Falha ao consultar o histórico: " + erro.getMessage());
        }
    }

    /** Recupera a análise mais recente e coloca seus valores no menu atual. */
    private static void carregarUltimoRegistro() {
        try {
            Optional<ResultadoIndices> ultimo = DAO.buscarUltimo();

            if (ultimo.isPresent()) {
                resultadoAtual = ultimo.get();
                System.out.println("Registro #" + resultadoAtual.getId() + " recuperado.");
                mostrarResultado(resultadoAtual);
            } else {
                System.out.println("Nenhum registro foi salvo ainda.");
            }
        } catch (SQLException erro) {
            System.out.println("Falha ao recuperar o último registro: " + erro.getMessage());
        }
    }

    /** Extensão opcional: recupera um registro específico pelo ID informado. */
    private static void buscarRegistroPorId() {
        int id = lerInteiroNaoNegativo("ID do registro: ");

        try {
            Optional<ResultadoIndices> encontrado = DAO.buscarPorId(id);

            if (encontrado.isPresent()) {
                resultadoAtual = encontrado.get();
                System.out.println("Registro #" + resultadoAtual.getId() + " encontrado.");
                mostrarResultado(resultadoAtual);
            } else {
                System.out.println("Nenhum registro com o ID #" + id + ".");
            }
        } catch (SQLException erro) {
            System.out.println("Falha ao buscar o registro: " + erro.getMessage());
        }
    }

    /** Descarta somente a digitação atual. O banco permanece intacto. */
    private static void iniciarNovaAnalise() {
        resultadoAtual = new ResultadoIndices();
        System.out.println("Nova análise iniciada. O histórico salvo foi preservado.");
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(TECLADO.nextLine());
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    private static int lerInteiroNaoNegativo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Digite zero ou um número positivo.");
        }
    }

    private static String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = TECLADO.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo não pode ficar vazio.");
        }
    }
}
