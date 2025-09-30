package com.calculator.deepmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.calculator.deepmarket.repositories.ChatRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatsServices {
    private final ChatRepository chatRepository;

    // logica del negocio
}
