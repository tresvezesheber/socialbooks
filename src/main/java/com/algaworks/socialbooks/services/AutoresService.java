package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.services.exceptions.AutorExistenteException;
import com.algaworks.socialbooks.services.exceptions.AutorNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;


    public List<Autor> listar() {
        return autoresRepository.findAll();
    }

    public Autor salvar(Autor autor) {
        if(autor.getId() != null) {
            Optional<Autor> autorOptional = autoresRepository.findById(autor.getId());

            if (autorOptional != null) {
                throw new AutorExistenteException("O autor já existe.");
            }
        }
        return autoresRepository.save(autor);
    }

    public Autor buscar(Long id) {
        Optional<Autor> autorOptional = autoresRepository.findById(id);

        if (!autorOptional.isPresent()) {
            throw new AutorNaoEncontradoException("O autor não pôde ser encontrado.");
        }
        return autorOptional.get();
    }
}
