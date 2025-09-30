package com.calculator.deepmarket.repositories;

import com.calculator.deepmarket.models.Post;
import com.calculator.deepmarket.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAll();

    // buscar post por id
    Optional<Post> findById(Integer id);
}
