package br.com.ucsal.olimpiadas.service;

public class DataLoader {

    private final ProvaService provaService;
    private final QuestaoService questaoService;

    public DataLoader(ProvaService provaService,
                      QuestaoService questaoService) {
        this.provaService = provaService;
        this.questaoService = questaoService;
    }

    public void carregarDadosIniciais() {

        // Criar prova
        var prova = provaService.cadastrarProva(
                "Olimpíada 2026 • Nível 1 • Prova A"
        );

        // Criar questão
        String enunciado = """
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """;

        String[] alternativas = {
                "A) Qh7#",
                "B) Qf5#",
                "C) Qc8#",
                "D) Qh8#",
                "E) Qe6#"
        };

        String fen = "6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1";

        questaoService.cadastrarQuestao(
                prova.getId(),
                enunciado,
                alternativas,
                'C',
                fen
        );
    }
}