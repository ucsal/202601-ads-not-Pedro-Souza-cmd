package br.com.ucsal.olimpiadas.repository;


import br.com.ucsal.olimpiadas.model.Participante;

import java.util.List;

public interface ParticipanteRepository {


    Participante salvar(Participante participante);

    List<Participante> listar();

    Participante buscarPorId(long id);



}
