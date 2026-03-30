package repository;


import model.Participante;

import java.util.List;

public interface ParticipanteRepository {


    Participante salvar(Participante participante);

    List<Participante> listar();

    Participante buscarPorId(long id);



}
