package com.treinarecife.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinarecife.blog.model.Comentario;
import com.treinarecife.blog.repository.ComentarioRepository;


@RestController
@RequestMapping(name = "/comentarios")
public class ComentarioController {
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<Page<Comentario>> listarComentarios(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> listarComentarioPeloId(@PathVariable("id") Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);

        if (comentario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(comentario.get());
    }

    @PostMapping
    public ResponseEntity<Comentario> cadastrarComentario(@RequestBody Comentario comentario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(comentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable("id") Long id, @RequestBody Comentario comentario) {
        Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);

        if (comentarioExistente.isPresent()) {
            comentarioExistente.get().setTexto(comentario.getTexto());
            comentarioExistente.get().setDataDeCriacao(comentario.getDataDeCriacao());

            return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.save(comentarioExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarComentarioPeloId(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);

        if (comentario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        comentarioRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comentario deletado com sucesso!");
    }
}
    

