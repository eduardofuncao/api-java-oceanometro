package org.example.services;

import org.example.entities.Doacao;
import org.example.entities.dtos.SearchDoacaoDto;
import org.example.repositories.DoacaoRepository;

public class DoacaoService {

    private DoacaoRepository doacaoRepository;

    public DoacaoService(){
        doacaoRepository = new DoacaoRepository();
    }

    public SearchDoacaoDto getAll(String nome, double preco,
                                   String orderBy, String direction, int limit, int offset){
        var doacaos = doacaoRepository.getAll(nome, preco, orderBy, direction, limit, offset);
        var totalItems = doacaoRepository.count(nome, preco);
        return new SearchDoacaoDto(doacaos);
    }

    public void create(Doacao doacao){
        var validation = doacao.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            doacaoRepository.create(doacao);
    }

    public void update(int id, Doacao doacao){
        var validation = doacao.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            doacaoRepository.update(id, doacao);
    }

    public void delete(int id){
        doacaoRepository.delete(id);
    }
}