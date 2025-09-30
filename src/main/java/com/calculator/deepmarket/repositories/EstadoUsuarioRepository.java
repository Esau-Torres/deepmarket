package com.calculator.deepmarket.repositories;
import com.calculator.deepmarket.models.EstadosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoUsuarioRepository extends JpaRepository<EstadosUsuario, Integer> {

}
