package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;
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
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {

        livro = livrosRepository.save(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Livro> livroOptional = livrosRepository.findById(id);

        //TODO improve this verification
        if (!livroOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livrosRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@RequestBody Livro livro, @PathVariable Long id) {
        livro.setId(id);
        livrosRepository.save(livro);
    }

}
