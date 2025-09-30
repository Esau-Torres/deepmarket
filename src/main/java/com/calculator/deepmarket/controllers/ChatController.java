package com.calculator.deepmarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Chat")
public class ChatController {

    @GetMapping
    public String Chat(Model model) {
        model.addAttribute("view", "Chats/ChatView");
        return "Layout/layout";
    }
}
