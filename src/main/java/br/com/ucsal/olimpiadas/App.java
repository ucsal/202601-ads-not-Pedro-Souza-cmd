package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import br.com.ucsal.olimpiadas.repository.inMemory.InMemoryParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.inMemory.InMemoryProvaRepository;
import br.com.ucsal.olimpiadas.repository.inMemory.InMemoryQuestaoRepository;
import br.com.ucsal.olimpiadas.repository.inMemory.InMemoryTentativaRepository;

import br.com.ucsal.olimpiadas.service.*;

import br.com.ucsal.olimpiadas.ui.MenuConsole;

public class App {

    public static void main(String[] args) {

        // ================= REPOSITORIES =================
        ParticipanteRepository participanteRepository = new InMemoryParticipanteRepository();
        ProvaRepository provaRepository = new InMemoryProvaRepository();
        QuestaoRepository questaoRepository = new InMemoryQuestaoRepository();
        TentativaRepository tentativaRepository = new InMemoryTentativaRepository();

        // ================= SERVICES =================
        ParticipanteService participanteService = new ParticipanteService(participanteRepository);
        ProvaService provaService = new ProvaService(provaRepository);
        QuestaoService questaoService = new QuestaoService(questaoRepository);
        AvaliacaoService avaliacaoService = new AvaliacaoService();

        AplicacaoProvaService aplicacaoProvaService =
                new AplicacaoProvaService(
                        questaoService,
                        tentativaRepository,
                        avaliacaoService
                );

        // ================= UI =================
        MenuConsole menu = new MenuConsole(
                participanteService,
                avaliacaoService,
                provaService,
                questaoService,
                aplicacaoProvaService
        );

        // ================= DATA LOADER =================
        DataLoader dataLoader = new DataLoader(provaService, questaoService);
        dataLoader.carregarDadosIniciais();

        // ================= START =================
        menu.iniciarMenu();
    }
}