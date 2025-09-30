package com.calculator.deepmarket.controllers;

import com.calculator.deepmarket.models.Usuario;
import com.calculator.deepmarket.services.EstadoServices;
import com.calculator.deepmarket.services.RolServices;
import com.calculator.deepmarket.services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UsuarioServices usuarioServices;
    private final RolServices rolService;
    private final EstadoServices estadoService;

    @GetMapping
    public String mostrarRegistro(Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        model.addAttribute("estados", estadoService.listarEstados());
        model.addAttribute("usuarioForm", new Usuario());
        model.addAttribute("view", "Login/RegisterView");
        return "Layout/layout";
    }

    // Registrar usuario
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute("usuarioForm") Usuario usuario,
            @RequestParam("archivo") MultipartFile archivo,
            RedirectAttributes redirectAttributes) {
        try {
            if (archivo != null && !archivo.isEmpty()) {
                String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
                Path ruta = Paths.get("src/main/resources/static/images/perfiles/" + nombreArchivo);
                Files.write(ruta, archivo.getBytes());
                usuario.setImagen(nombreArchivo); // Guardamos el nombre en la BD
            }
            usuarioServices.agregarUsuario(usuario);
            redirectAttributes.addFlashAttribute("successMessage", "Usuario registrado con éxito ");

        } catch (IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Error al subir la imagen ");
        }

        return "redirect:/register";
    }

    // Recuperar cuenta por correo
    @PostMapping("/recuperar")
    public String RecuperarCuenta(@RequestParam("correo") String correo, RedirectAttributes redirectAttributes) {
        try {
            usuarioServices.RecuperarCorreo(correo);
            redirectAttributes.addFlashAttribute("successMessage", "Revisa tu correo para acceder a tu cuenta.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "Layout/layout";
    }

}
