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

        String enunciado2 = """
        Questão 2 — Mate em 1.
        É a vez das brancas.
        Encontre o lance que dá mate imediatamente.
        """;

        String[] alternativas2 = {
                "A) Qg7#",
                "B) Qb7#",
                "C) Qd8#",
                "D) Qf8#",
                "E) Qe7#"
        };

        String fen2 = "7k/6pp/8/8/8/6Q1/6PP/6K1 w - - 0 1";

        questaoService.cadastrarQuestao(
                prova.getId(),
                enunciado2,
                alternativas2,
                'C',
                fen2
        );

        String enunciado3 = """
        Questão 3 — Mate em 1.
        É a vez das brancas.
        Qual lance finaliza a partida?
        """;

        String[] alternativas3 = {
                "A) Qh6#",
                "B) Qg5#",
                "C) Qc8#",
                "D) Qf7#",
                "E) Qd6#"
        };

        String fen3 = "6k1/5ppp/8/8/8/6Q1/6PP/6K1 w - - 0 1";

        questaoService.cadastrarQuestao(
                prova.getId(),
                enunciado3,
                alternativas3,
                'C',
                fen3
        );

        String enunciado4 = """
        Questão 4 — Mate em 1.
        É a vez das brancas.
        Encontre o xeque-mate imediato.
        """;

        String[] alternativas4 = {
                "A) Qh7#",
                "B) Qg8#",
                "C) Qd8#",
                "D) Qe7#",
                "E) Qf6#"
        };

        String fen4 = "7k/6pp/8/8/8/7Q/6PP/6K1 w - - 0 1";

        questaoService.cadastrarQuestao(
                prova.getId(),
                enunciado4,
                alternativas4,
                'B',
                fen4
        );

    }
}