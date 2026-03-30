package br.com.ucsal.olimpiadas.repository.inMemory;

import br.com.ucsal.olimpiadas.model.Prova;

import br.com.ucsal.olimpiadas.repository.ProvaRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProvaRepository implements ProvaRepository {

    private final List<Prova> provas = new ArrayList<>();
    private long proximoId = 1;

    @Override
    public Prova salvar(Prova prova){
        prova.setId(proximoId++);
        provas.add(prova);
        return prova;
    }

    public List<Prova> listar(){
        return provas;
    }

    public Prova buscarPorId(long id){
        return provas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }






}
