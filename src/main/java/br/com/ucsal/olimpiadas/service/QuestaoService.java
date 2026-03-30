package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

import java.util.List;

public class QuestaoService {

    private final QuestaoRepository questaoRepository;

    public  QuestaoService(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public Questao cadastrarQuestao(Long provaId, String enunciado,
                                    String[] alternativas, char correta,
                                    String fen) {

        if (enunciado == null || enunciado.isBlank()) {
            throw new IllegalArgumentException("Enunciado invalido");
        }

        Questao questao = new Questao();
        questao.setProvaId(provaId);
        questao.setEnunciado(enunciado);
        questao.setAlternativas(alternativas);
        questao.setAlternativaCorreta(correta);
        questao.setFenInicial(fen);

        return questaoRepository.salvar(questao);
    }

    public List<Questao> buscarPorProva(long provaId) {
        return questaoRepository.buscarPorProvaId(provaId);
    }
}
