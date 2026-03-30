package service;

import model.Participante;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteService {

    private long proximoID = 1;
    private final List<Participante> p = new ArrayList<>();

    public Participante cadastrarParticipante(String nome, String email){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome Invalido");
        }

        Participante Participante = new Participante();
        Participante.setId(proximoID++);
        Participante.setNome(nome);
        Participante.setEmail(email);
        p.add(Participante);

        return Participante;
    }

    public List<Participante> listarPartiticpantes() {
        return p;
    }

    public Participante buscarPorId(long id){
        return p.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
