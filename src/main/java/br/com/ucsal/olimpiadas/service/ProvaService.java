package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;

import java.util.List;

public class ProvaService {

    private final ProvaRepository provaRepository;

    public ProvaService(ProvaRepository provaRepository) {
        this.provaRepository = provaRepository;
    }

    public Prova cadastrarProva(String titulo){
        if (titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("Titulo Invalido");
        }

        Prova prova = new Prova();
        prova.setTitulo(titulo);

        return provaRepository.salvar(prova);
    }

    public List<Prova> listarProva(){
        return provaRepository.listar();
    }

    public Prova buscarPorId(long id) {
        return provaRepository.buscarPorId(id);
    }



}
