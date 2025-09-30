package com.calculator.deepmarket.services;

import com.calculator.deepmarket.models.Rol;
import com.calculator.deepmarket.repositories.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RolServices {
    private final RolRepository rolRepository;

    // mostrar todos los roles en el select
    public List<Rol> listarRoles(){
        return rolRepository.findAll();
    }
}
