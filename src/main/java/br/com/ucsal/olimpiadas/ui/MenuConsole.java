package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.*;
import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {

   private final ParticipanteService participanteService;
   private final AvaliacaoService avaliacaoService;
   private final ProvaService provaService;
   private final QuestaoService questaoService;
   private final AplicacaoProvaService aplicacaoProvaService;


   private final Scanner sc = new Scanner(System.in);

   public MenuConsole(ParticipanteService participanteService,
                      AvaliacaoService avaliacaoService,
                      ProvaService provaService,
                      QuestaoService questaoService,
                      AplicacaoProvaService aplicacaoProvaService){
       this.participanteService = participanteService;
       this.avaliacaoService = avaliacaoService;
       this.provaService = provaService;
       this.questaoService = questaoService;
       this.aplicacaoProvaService = aplicacaoProvaService;
   }

   public void IniciarMenu(){
       while (true) {
           System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V3) ===");
           System.out.println("1) Cadastrar participante");
           System.out.println("2) Cadastrar prova");
           System.out.println("3) Cadastrar questão");
           System.out.println("4) Aplicar prova");
           System.out.println("5) Listar tentativas");
           System.out.println("0) Sair");
           System.out.print("> ");

           switch (sc.nextLine()) {
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

   public void cadastrarParticipante(){
       System.out.println("Digite o nome do participante: ");
       String nome = sc.nextLine();

       System.out.println("Digite o email do participante (Opicional): ");
       String email = sc.nextLine();

       try{
           Participante p = participanteService.cadastrarParticipante(nome, email);
           System.out.println("Participante Cadastrado: " + p.getId());
       } catch(Exception e){
           System.out.println(e.getMessage());
       }

   }

   public void cadastrarProva(){
       System.out.println("Digite o titulo da prova: ");
       String titulo = sc.nextLine();

       try {
           Prova prova = provaService.cadastrarProva(titulo);
           System.out.println("Prova Cadastrada: " + prova.getId());
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }

   public void cadastrarQuestao(){
       System.out.print("ID da prova: ");
       long provaId = Long.parseLong(sc.nextLine());

       System.out.println("Enunciado:");
       String enunciado = sc.nextLine();

       String[] alternativas = new String[5];

       for (int i = 0; i < 5; i++) {
           char letra = (char) ('A' + i);
           System.out.print("Alternativa " + letra + ": ");
           alternativas[i] = letra + ") " + sc.nextLine();
       }

       System.out.print("Correta (A-E): ");
       char correta = sc.nextLine().charAt(0);

       System.out.print("FEN inicial: ");
       String fen = sc.nextLine();

       try {
           questaoService.cadastrarQuestao(provaId, enunciado, alternativas, correta, fen);
           System.out.println("Questão cadastrada!");
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }

   }

    public void aplicarProva() {

        System.out.print("ID do participante: ");
        long participanteId = Long.parseLong(sc.nextLine());

        System.out.print("ID da prova: ");
        long provaId = Long.parseLong(sc.nextLine());

        var questoes = questaoService.buscarPorProva(provaId);

        if (questoes.isEmpty()) {
            System.out.println("Prova sem questões");
            return;
        }

        List<Character> respostas = new ArrayList<>();

        System.out.println("\n--- Início da Prova ---");

        for (var q : questoes) {
            System.out.println("\n" + q.getEnunciado());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Resposta: ");
            respostas.add(sc.nextLine().charAt(0));
        }

        try {
            var tentativa = aplicacaoProvaService
                    .aplicarProva(participanteId, provaId, respostas);

            int nota = avaliacaoService.calcularNota(tentativa);

            System.out.println("\n--- Fim da Prova ---");
            System.out.println("Nota: " + nota + "/" + tentativa.getRespostas().size());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarTentativas() {

        System.out.println("\n--- Tentativas ---");

        var tentativas = aplicacaoProvaService.listarTentativas();

        if (tentativas.isEmpty()) {
            System.out.println("Nenhuma tentativa encontrada.");
            return;
        }

        for (var t : tentativas) {

            int nota = avaliacaoService.calcularNota(t);

            System.out.printf(
                    "#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(),
                    t.getParticipanteId(),
                    t.getProvaId(),
                    nota,
                    t.getRespostas().size()
            );
        }
    }






}