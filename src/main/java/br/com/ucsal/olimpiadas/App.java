package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.*;
import br.com.ucsal.olimpiadas.repository.inMemory.*;
import br.com.ucsal.olimpiadas.service.*;

import java.util.Scanner;

public class App {

    // ================= REPOSITORIES =================
    private static final ParticipanteRepository participanteRepository =
            new InMemoryParticipanteRepository();

    private static final ProvaRepository provaRepository =
            new InMemoryProvaRepository();

    private static final QuestaoRepository questaoRepository =
            new InMemoryQuestaoRepository();

    private static final TentativaRepository tentativaRepository =
            new InMemoryTentativaRepository();


    // ================= SERVICES =================
    private static final ParticipanteService participanteService =
            new ParticipanteService(participanteRepository);

    private static final ProvaService provaService =
            new ProvaService(provaRepository);

    private static final QuestaoService questaoService =
            new QuestaoService(questaoRepository);

    private static final AvaliacaoService avaliacaoService =
            new AvaliacaoService();

    private static final AplicacaoProvaService aplicacaoProvaService =
            new AplicacaoProvaService(
                    questaoService,
                    tentativaRepository,
                    avaliacaoService
            );


    private static final Scanner in = new Scanner(System.in);


    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V2) ===");
            System.out.println("1) Cadastrar participante");
            System.out.println("2) Cadastrar prova");
            System.out.println("3) Cadastrar questão");
            System.out.println("4) Aplicar prova");
            System.out.println("5) Listar tentativas");
            System.out.println("0) Sair");
            System.out.print("> ");

            switch (in.nextLine()) {
                case "1" -> cadastrarParticipante();
                case "2" -> cadastrarProva();
                case "3" -> cadastrarQuestao();
                case "4" -> aplicarProva();
                case "5" -> listarTentativas();
                case "0" -> {
                    System.out.println("tchau");
                    return;
                }
                default -> System.out.println("opção inválida");
            }
        }
    }

    // ================= MÉTODOS (AINDA SIMPLES) =================

    static void cadastrarParticipante() {
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("Email: ");
        String email = in.nextLine();

        try {
            Participante p = participanteService.cadastrarParticipante(nome, email);
            System.out.println("Participante cadastrado: " + p.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void cadastrarProva() {
        System.out.print("Título: ");
        String titulo = in.nextLine();

        try {
            Prova prova = provaService.cadastrarProva(titulo);
            System.out.println("Prova criada: " + prova.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void cadastrarQuestao() {

        System.out.print("ID da prova: ");
        long provaId = Long.parseLong(in.nextLine());

        System.out.println("Enunciado:");
        String enunciado = in.nextLine();

        String[] alternativas = new String[5];

        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Correta (A-E): ");
        char correta = in.nextLine().charAt(0);

        System.out.print("FEN inicial: ");
        String fen = in.nextLine();

        try {
            questaoService.cadastrarQuestao(provaId, enunciado, alternativas, correta, fen);
            System.out.println("Questão cadastrada!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void aplicarProva() {

        System.out.print("ID do participante: ");
        long participanteId = Long.parseLong(in.nextLine());

        System.out.print("ID da prova: ");
        long provaId = Long.parseLong(in.nextLine());

        var questoes = questaoService.buscarPorProva(provaId);

        if (questoes.isEmpty()) {
            System.out.println("Prova sem questões");
            return;
        }

        var respostas = new java.util.ArrayList<Character>();

        System.out.println("\n--- Início da Prova ---");

        for (var q : questoes) {
            System.out.println("\n" + q.getEnunciado());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Resposta: ");
            respostas.add(in.nextLine().charAt(0));
        }

        try {
            Tentativa tentativa = aplicacaoProvaService
                    .aplicarProva(participanteId, provaId, respostas);

            int nota = avaliacaoService.calcularNota(tentativa);

            System.out.println("\n--- Fim da Prova ---");
            System.out.println("Nota: " + nota + "/" + tentativa.getRespostas().size());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void listarTentativas() {

        System.out.println("\n--- Tentativas ---");

        for (var t : tentativaRepository.listarTentativas()) {
            int nota = avaliacaoService.calcularNota(t);

            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(),
                    t.getParticipanteId(),
                    t.getProvaId(),
                    nota,
                    t.getRespostas().size());
        }
    }
}