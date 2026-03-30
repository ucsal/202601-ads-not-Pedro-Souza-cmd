package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;

public class AvaliacaoService {


    public int calcularNota(Tentativa tentativa){
        int acertos = 0;

        for (Resposta resposta : tentativa.getRespostas()) {
            if (resposta.isCorreta()){
                acertos++;
            }
        }

        return acertos;
    }


}
