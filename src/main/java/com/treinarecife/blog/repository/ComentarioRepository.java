package com.treinarecife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinarecife.blog.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Long>{
    
}
