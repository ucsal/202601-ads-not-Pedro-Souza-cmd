package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import java.util.List;

public class AplicacaoProvaService {


    private final QuestaoService questaoService;
    private final TentativaRepository tentativaRepository;
    private final AvaliacaoService avaliacaoService;

    public AplicacaoProvaService(QuestaoService questaoService,
                                 TentativaRepository tentativaRepository,
                                 AvaliacaoService avaliacaoService) {
        this.questaoService = questaoService;
        this.tentativaRepository = tentativaRepository;
        this.avaliacaoService = avaliacaoService;
    }

    public Tentativa aplicarProva(long participanteId,
                                  long provaId,
                                  List<Character> respostasMarcadas){

        List<Questao> questoes = questaoService.buscarPorProva(provaId);

        if (questoes.isEmpty()){
            throw new IllegalArgumentException("Prova não possui questões");
        }

        Tentativa tentativa = new Tentativa();
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        for (int i = 0; i < respostasMarcadas.size(); i++) {
            Questao questao = questoes.get(i);
            char respostaMarcarda;

            try {
                respostaMarcarda = Questao.normalizar(respostasMarcadas.get(i));
            } catch (Exception e) {
                respostaMarcarda = 'X';
            }

            Resposta resposta = new Resposta();
            resposta.setQuestaoId(questao.getId());
            resposta.setAlternativaMarcada(respostaMarcarda);
            resposta.setCorreta(questao.isRespostaCorreta(respostaMarcarda));

            tentativa.getRespostas().add(resposta);
        }

        Tentativa tentativaSalva = tentativaRepository.salvar(tentativa);

        int nota = avaliacaoService.calcularNota(tentativaSalva);

        return tentativaSalva;

    }

    public List<Tentativa> listarTentativas() {
        return tentativaRepository.listarTentativas();
    }





}
