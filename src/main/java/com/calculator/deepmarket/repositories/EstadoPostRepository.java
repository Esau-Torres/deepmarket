package com.calculator.deepmarket.repositories;
import com.calculator.deepmarket.models.EstadosPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPostRepository extends JpaRepository<EstadosPost, Integer> {

}
