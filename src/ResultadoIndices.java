/**
 * Representa um conjunto de índices já calculados pelos alunos.
 *
 * IMPORTANTE: esta classe não calcula percentuais nem médias. Os valores são
 * obtidos anteriormente na planilha ou na investigação dos dados. O programa
 * apenas recebe, organiza, exibe e, na versão 2.0, salva esses valores.
 */
public class ResultadoIndices {
    // Identificador criado automaticamente pelo banco. Zero significa "ainda não salvo".
    private long id;

    // Data e hora registradas pelo SQLite. Fica vazia enquanto o objeto não for salvo.
    private String criadoEm;

    // Quantidade de registros que permaneceram depois do tratamento da base.
    private int totalValidos;

    // Nome do candidato que será apresentado no resultado.
    private String nomeCandidato;

    // Quantidade já contada para o candidato.
    private int totalCandidato;

    // Percentual do candidato já calculado na planilha.
    private String percentualCandidato;

    // Média de satisfação já calculada na planilha.
    private String satisfacaoMedia;

    // Quantidade já contada para o tema Saúde.
    private int totalSaude;

    // Percentual de Saúde já calculado na planilha.
    private String percentualSaude;

    /** Construtor usado quando o aluno começa uma nova digitação. */
    public ResultadoIndices() {
        this(0, "", 0, "Não informado", 0, "Não informado",
             "Não informada", 0, "Não informado");
    }

    /** Construtor usado pelo DAO para transformar uma linha do banco em objeto. */
    public ResultadoIndices(long id, String criadoEm, int totalValidos,
                            String nomeCandidato, int totalCandidato,
                            String percentualCandidato, String satisfacaoMedia,
                            int totalSaude, String percentualSaude) {
        this.id = id;
        this.criadoEm = criadoEm;
        this.totalValidos = totalValidos;
        this.nomeCandidato = nomeCandidato;
        this.totalCandidato = totalCandidato;
        this.percentualCandidato = percentualCandidato;
        this.satisfacaoMedia = satisfacaoMedia;
        this.totalSaude = totalSaude;
        this.percentualSaude = percentualSaude;
    }

    public long getId() {
        return id;
    }

    public String getCriadoEm() {
        return criadoEm;
    }

    public int getTotalValidos() {
        return totalValidos;
    }

    public void setTotalValidos(int totalValidos) {
        this.totalValidos = totalValidos;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public int getTotalCandidato() {
        return totalCandidato;
    }

    public String getPercentualCandidato() {
        return percentualCandidato;
    }

    /** Guarda, de uma só vez, os três dados já prontos do candidato. */
    public void informarCandidato(String nomeCandidato, int totalCandidato,
                                  String percentualCandidato) {
        this.nomeCandidato = nomeCandidato;
        this.totalCandidato = totalCandidato;
        this.percentualCandidato = percentualCandidato;
    }

    public String getSatisfacaoMedia() {
        return satisfacaoMedia;
    }

    public void setSatisfacaoMedia(String satisfacaoMedia) {
        this.satisfacaoMedia = satisfacaoMedia;
    }

    public int getTotalSaude() {
        return totalSaude;
    }

    public void setTotalSaude(int totalSaude) {
        this.totalSaude = totalSaude;
    }

    public String getPercentualSaude() {
        return percentualSaude;
    }

    public void setPercentualSaude(String percentualSaude) {
        this.percentualSaude = percentualSaude;
    }

    /**
     * Verifica somente se os campos necessários foram informados.
     * Não recalcula nem julga os índices encontrados pelos alunos.
     */
    public boolean estaCompleto() {
        return totalValidos > 0
            && nomeCandidato != null
            && !nomeCandidato.trim().isEmpty()
            && !"Não informado".equals(nomeCandidato)
            && percentualCandidato != null
            && !"Não informado".equals(percentualCandidato)
            && satisfacaoMedia != null
            && !"Não informada".equals(satisfacaoMedia)
            && percentualSaude != null
            && !"Não informado".equals(percentualSaude);
    }

    /** Monta a conclusão textual. Os números são apenas exibidos. */
    public String gerarConclusao() {
        return String.format(
            "Na base tratada, %s apareceu %d vezes (%s de %d registros válidos).%n" +
            "A satisfação média foi %s de 10.%n" +
            "O tema Saúde apareceu %d vezes (%s).%n" +
            "Conclusão: os números descrevem apenas a base da atividade e não uma eleição real.",
            nomeCandidato,
            totalCandidato,
            percentualCandidato,
            totalValidos,
            satisfacaoMedia,
            totalSaude,
            percentualSaude
        );
    }
}
