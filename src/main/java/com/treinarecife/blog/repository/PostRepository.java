package com.treinarecife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinarecife.blog.model.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

}
