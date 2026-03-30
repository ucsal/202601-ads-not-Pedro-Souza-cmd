package br.com.ucsal.olimpiadas.repository.inMemory;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryQuestaoRepository implements QuestaoRepository {


    private final List<Questao> questoes = new ArrayList<>();
    private long proximoId = 1;

    @Override
    public Questao salvar(Questao questao) {
        questao.setId(proximoId++);
        questoes.add(questao);
        return questao;
    }

    @Override
    public List<Questao> listar(){
        return questoes;
    }

    @Override
    public List<Questao> buscarPorProvaId(long provaId) {
        return questoes.stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();
    }








}
