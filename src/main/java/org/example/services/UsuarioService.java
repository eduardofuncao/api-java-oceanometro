package org.example.services;

import org.example.entities.Usuario;
import org.example.entities.dtos.SearchUsuarioDto;
import org.example.repositories.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }

    public SearchUsuarioDto getAll(String nome, double preco,
                                   String orderBy, String direction, int limit, int offset){
        var usuarios = usuarioRepository.getAll(nome, preco, orderBy, direction, limit, offset);
        var totalItems = usuarioRepository.count(nome, preco);
        return new SearchUsuarioDto(usuarios);
    }

    public void create(Usuario usuario){
        var validation = usuario.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.create(usuario);
    }

    public void update(int id, Usuario usuario){
        var validation = usuario.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.update(id, usuario);
    }

    public void delete(int id){
        usuarioRepository.delete(id);
    }
}