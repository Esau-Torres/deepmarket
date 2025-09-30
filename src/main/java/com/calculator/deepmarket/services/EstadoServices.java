package com.calculator.deepmarket.services;

import com.calculator.deepmarket.models.EstadosUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.calculator.deepmarket.repositories.EstadoUsuarioRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EstadoServices {
    private final EstadoUsuarioRepository estadoUsuarioRepository;

    // mostrar todos los estados
    public List<EstadosUsuario> listarEstados(){
        return estadoUsuarioRepository.findAll();
    }

}
