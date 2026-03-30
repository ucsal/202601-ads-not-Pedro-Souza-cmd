package br.com.ucsal.olimpiadas.repository.inMemory;

import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTentativaRepository implements TentativaRepository {


    private final List<Tentativa> tentativas = new ArrayList<>();
    private long proximoID = 1;

    @Override
    public Tentativa salvar(Tentativa tentativa) {
        tentativa.setId(proximoID++);
        tentativas.add(tentativa);

        return tentativa;
    }

    @Override
    public List<Tentativa> listarTentativas() {
        return tentativas;
    }





}
