package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Livro> buscar(@PathVariable Long id) {
        return livrosRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livrosRepository.deleteById(id);
    }

}
