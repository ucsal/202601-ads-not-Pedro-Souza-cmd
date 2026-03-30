package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Prova;
import java.util.List;

public interface ProvaRepository {

    Prova salvar(Prova prova);

    List<Prova> listar();

    Prova buscarPorId(long id);


}
