package com.calculator.deepmarket;

import com.calculator.deepmarket.models.EstadosUsuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.calculator.deepmarket.repositories.UsuarioRepository;
import com.calculator.deepmarket.models.Usuario;
import com.calculator.deepmarket.models.Rol;
import com.calculator.deepmarket.models.EstadosUsuario;
import org.springframework.boot.CommandLineRunner; // mostrar pruebas en consola
import org.springframework.context.annotation.Bean; //crea el bean
//security
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DeepmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeepmarketApplication.class, args);
    }

    //@Bean
    //public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    //}
};
    //@Bean
    //CommandLineRunner demo(UsuarioRepository usuarioRepository) {
    //  return (args) -> {

            //buscar todos los registros
            // for (Usuario u : usuarioRepository.findAll()) {
            //     System.out.println(" - " + u.getId() + " - " + u.getNombre());
            // }

            // Agregar
            //Usuario u = new Usuario();
            //u.setNombre("Dariela");
            //u.setApellido("Martinez");
            //u.setEdad(13);
            //u.setSexo("F");
            //u.setFechaNacimiento(java.time.LocalDateTime.parse("2008-01-01T00:00:00"));
            //u.setUsuario("user");
            //u.setCorreo("Dariela@gmail.com");
            //u.setFechaCreacion(java.time.LocalDateTime.now());
            //u.setPassword("user123");
            //u.setIdRol(new Rol(2,"CLIENTE"));
            //u.setIdEstado(new EstadosUsuario(1,"ACTIVO"));
            //usuarioRepository.save(u);
            //System.out.println("prueba completa ");

            // eliminar
            // Usuario u = usuarioRepository.findById(2).get();
            //usuarioRepository.delete(u);
            //System.out.println("prueba completa ");

            //Usuario u = usuarioRepository.findByCorreo("davidesau140@gmail.com").get();
            //System.out.println(" - " + u.getCorreo());
//};
// }

//}