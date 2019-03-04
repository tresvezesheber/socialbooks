package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosRepository livrosRepository;


    @GetMapping
    public List<Livro> listar() {
       return livrosRepository.findAll();
    }

    @PostMapping
    public void salvar(@RequestBody Livro livro) {
        livrosRepository.save(livro);
    }
}
