package com.calculator.deepmarket.services;

import com.calculator.deepmarket.models.Post;
import com.calculator.deepmarket.models.Usuario;
import com.calculator.deepmarket.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServices {
    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> buscarPostPorId(Integer id) {
        return postRepository.findById(id);
    }
}
