package com.calculator.deepmarket.repositories;
import com.calculator.deepmarket.models.EdicionesMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdicionesMensajeRepository extends JpaRepository<EdicionesMensaje, Integer> {
}
