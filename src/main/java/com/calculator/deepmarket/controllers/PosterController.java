package com.calculator.deepmarket.controllers;

import com.calculator.deepmarket.models.Post;
import com.calculator.deepmarket.services.PostServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/postea")
@RequiredArgsConstructor
public class PosterController {

    private final PostServices postService;

    @GetMapping
    public String mostrarPost(Model model){
        model.addAttribute("Posts", postService.findAll());
        model.addAttribute("view", "Post/PostView");
        return "Layout/layout";
    }

    @GetMapping("/")
    public String formpost(Model model) {
        model.addAttribute("view", "Form/formPostView");
        return "Layout/layout";
    }

    // buscar datos del post por id
    @GetMapping("/data/{id}")
    @ResponseBody
    public Post buscarUsuario(@PathVariable Integer id) {
        return postService.buscarPostPorId(id).orElseThrow(() -> new IllegalArgumentException("Informacion del post no encontrado"));
    }
}
