package repository.inMemory;

import model.Participante;
import repository.ParticipanteRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryParticipanteRepository implements ParticipanteRepository {

    private final List<Participante> p = new ArrayList<Participante>();

    private long proximoId = 1;


    @Override
    public Participante salvar(Participante participante) {

        participante.setId(proximoId++);
        p.add(participante);

        return participante;
    }


    @Override
    public List<Participante> listar() {
        return p;
    }

    @Override
    public Participante buscarPorId(long id){
        return p.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }













}
