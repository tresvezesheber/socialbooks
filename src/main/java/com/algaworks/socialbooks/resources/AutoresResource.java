package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.services.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresResource {

    @Autowired
    private AutoresService autoresService;


    @GetMapping
    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
    }


}
