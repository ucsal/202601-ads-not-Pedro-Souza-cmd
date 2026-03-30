package service;

import model.Participante;
import repository.ParticipanteRepository;


import java.util.List;

public class ParticipanteService {

    private final ParticipanteRepository repo;

    public ParticipanteService(ParticipanteRepository repository){
        this.repo = repository;
    }

    public Participante cadastrarParticipante(String nome, String email){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome Invalido");
        }

        Participante participante = new Participante();
        participante.setNome(nome);
        participante.setEmail(email);

        return repo.salvar(participante);

    }

    public List<Participante> listarParticipante(){
        return repo.listar();
    }

    public Participante buscarPorId(long id){
        return repo.buscarPorId(id);
    }
}
