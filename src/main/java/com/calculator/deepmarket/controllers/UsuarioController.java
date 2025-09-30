package com.calculator.deepmarket.controllers;

import com.calculator.deepmarket.models.Usuario;
import com.calculator.deepmarket.services.EstadoServices;
import com.calculator.deepmarket.services.UsuarioServices;
import com.calculator.deepmarket.services.RolServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/Usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioServices usuarioService;
    private final RolServices rolService;
    private final EstadoServices estadoService;

    // Mostrar vista
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("Usuarios", usuarioService.listarUsuario());
        model.addAttribute("roles", rolService.listarRoles());
        model.addAttribute("estados", estadoService.listarEstados());
        model.addAttribute("usuarioForm", new Usuario());
        model.addAttribute("view", "Usuarios/UsuariosView");
        return "Layout/layout";
    }

    // Guardar usuario
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
            usuarioService.agregarUsuario(usuario);
            redirectAttributes.addFlashAttribute("successMessage", "Usuario guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Error al subir la imagen ❌");
        }
        return "redirect:/Usuarios";
    }

    // eliminar usuario
    @PostMapping("/eliminarUsuario")
    public String eliminar(@RequestParam("eliminarId") Integer id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.eliminarUsuarioPorId(id);
            redirectAttributes.addFlashAttribute("successMessage", "Usuario eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/Usuarios";
    }

    // Editar usuario
    @PostMapping("/editar")
    public String editar(@ModelAttribute("usuarioForm") Usuario usuario, RedirectAttributes redirectAttributes) { //RedirectAttributes para manejo de errores
        try {
            usuarioService.actualizarUsuario(usuario, usuario.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Usuario Editado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage()); // manejo de errores
        }
        return "redirect:/Usuarios";
    }

    // editar contraseña
    @PostMapping("/editarContrasena")
    public String editarContrasena(@RequestParam("id") Integer id, @RequestParam("password") String pass, @RequestParam("contrasena") String contrasenaNueva, @RequestParam("confirmarContrasena") String confirmarContrasena, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.cambiarContrasenaDeUsuario(id, pass, contrasenaNueva, confirmarContrasena);
            redirectAttributes.addFlashAttribute("successMessage", "Contraseña cambiada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/Usuarios";
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Usuario buscarUsuario(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    // perfil de usuario
    @GetMapping("/perfil")
    public String perfil(Model model) {
        model.addAttribute("view", "Usuarios/PerfilUserView");
        return "Layout/layout";
    }


    // acceso denegado
    @GetMapping("/accesoDenegado")
    public String accesoDenegado(Model model) {
        return "Home/AccesoDenegadoView";
    }

    // login
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("passwordSesion") String password, RedirectAttributes redirectAttributes, HttpSession session) {

        Usuario User = usuarioService.autenticarUsuario(username, password);

        if(User != null){
            session.setAttribute("usuario", User);
            redirectAttributes.addFlashAttribute("successMessage", "Bienvenvenido " +  User.getNombre() + " a DeepMarket");
        }
        else{
            redirectAttributes.addFlashAttribute("errorMessage", "Usuario o contraseña incorrecta");
        }
        return "redirect:/home";
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "Se cerro sesión correctamente");
        return "redirect:/home";
    }

}
