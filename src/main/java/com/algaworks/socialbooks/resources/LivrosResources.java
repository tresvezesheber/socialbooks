package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosService livrosService;


    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {

        livro = livrosService.salvar(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Livro livro = livrosService.buscar(id);

        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);

        //TODO Cache-Control →max-age=20 é enviado no header, porém não funciona
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS)).body(livro);
//        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable Long id) {
        livro.setId(id);
        livrosService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @Valid @RequestBody Comentario comentario) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        comentario.setUsuario(auth.getName());
        livrosService.salvarComentario(livroId, comentario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId) {
        List<Comentario> comentarios = livrosService.listarComentarios(livroId);
        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }
}
