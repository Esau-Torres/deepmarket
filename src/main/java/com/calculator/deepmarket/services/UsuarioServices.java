package com.calculator.deepmarket.services;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.calculator.deepmarket.liberia.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
//======================modelos=================================

import com.calculator.deepmarket.models.Usuario;
import com.calculator.deepmarket.repositories.UsuarioRepository;

// se ponen los modelos de roles y estados por que esta relacionada con la tabla usuario
import com.calculator.deepmarket.models.Rol;
import com.calculator.deepmarket.repositories.RolRepository;
import com.calculator.deepmarket.models.EstadosUsuario;
import com.calculator.deepmarket.repositories.EstadoUsuarioRepository;
import org.springframework.util.Assert;
import org.springframework.web.context.request.AsyncWebRequestInterceptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServices {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final EstadoUsuarioRepository estadoUsuarioRepository;
    private final EmailServices emailServices;
    private final AsyncWebRequestInterceptor asyncWebRequestInterceptor;

    // metodos internos para validacion
    private void validarUsuario(String usuario){
        Assert.hasText(usuario, "El usuario esta vacio");
        Assert.notNull(usuario, "El usuario no puede hacer nulo");

        if (usuarioRepository.findByUsuario(usuario).isPresent()){
            throw new IllegalArgumentException("El usuario "+ usuario +" ya existe");
        }
    }

    //funcion para buscar usuario por id
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // metodo para validar correo
    private void validarCorreo(String correo){
        Assert.hasText(correo, "El correo esta vacio");
        Assert.notNull(correo, "El correo no puede hacer nulo");
        if (usuarioRepository.findByCorreo(correo).isPresent()){
            throw new IllegalArgumentException("El correo "+ correo +" ya existe");
        }
    }

    //funcion para validar si existe un rol por id
    private void validarRol(Integer id){
        Assert.notNull(id, "El id no puede hacer nulo");
        if (!rolRepository.existsById(id)){
            throw new IllegalArgumentException("El rol no existe");
        }
    }

    //funcion para validar si existe un estado por id
    private void validarEstado(Integer id){
        Assert.notNull(id, "El id no puede hacer nulo");
        if (!estadoUsuarioRepository.existsById(id)){
            throw new IllegalArgumentException("El Estado no existe");
        }
    }

    // Listar todos los usuarios
    @Transactional(readOnly = true) //  se utiliza cuando la transaccion no genere una modificacion en la base de datos solo se ocupa para leer
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    // listar usuarios activos
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarioActivos() {
        return usuarioRepository.findEstadoByNombre("ACTIVO");
    }

    // buscar usuario por email
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        Assert.hasText(email, "El email esta vacio");
        return usuarioRepository.findByCorreo(email);
    }

    // eliminar usuario por id
    public void eliminarUsuarioPorId(Integer id) {
        Assert.notNull(id, "El id no puede hacer nulo");
        if (!usuarioRepository.existsById(id)){
            throw new IllegalArgumentException("El id no existe");
        }
        usuarioRepository.deleteById(id);
    }

    // cambiar contraseña de usuario
    public Usuario cambiarContrasenaDeUsuario(Integer id, String pass ,String contrasena, String confirmarContrasena ) {
        Assert.notNull(id, "El id no puede hacer nulo");
        Assert.hasText(contrasena, "La contraseña esta vacia");
        Assert.hasText(confirmarContrasena, "La confirmacion de contraseña no puede ir vacia");
        Assert.hasText(pass, "La contraseña actual esta vacia");

        if (!contrasena.equals(confirmarContrasena)) {
            throw new IllegalArgumentException("Las contraseñas nuevas no coinciden");
        }

        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El Usuario no existe");
        }

        Usuario usuario = usuarioRepository.findById(id).get();
        String ContraActual = usuario.getPassword();

        // validar si la contraseña actual es correcta
        if(!PasswordUtils.matches(pass, ContraActual)){
            throw new IllegalArgumentException("La contraseña actual no coincide");
        }
        validarContrasena(contrasena, ContraActual);

        usuario.setPassword(PasswordUtils.hash(contrasena)); // aqui password
        return usuarioRepository.save(usuario);
    }


    //validar contraseñas
    private void validarContrasena(String contrasena, String actual) {
        if (contrasena.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        if (PasswordUtils.matches(contrasena, actual)) {
            throw new IllegalArgumentException("La contraseña no puede ser igual a la actual");
        }
        if (!contrasena.matches(".*\\d.*")) {
            throw new IllegalArgumentException("La contraseña debe tener al menos un número");
        }
        if (!contrasena.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("La contraseña debe tener al menos una mayúscula");
        }
    }


    // funcion para agregar usurarios nuevos
    public Usuario agregarUsuario(Usuario usuario) { // se recibe un objeto llamado usuario
        Assert.notNull(usuario, "El usuario no puede ser nulo");
        Assert.hasText(usuario.getNombre(), "El correo no puede ser nulo");
        Assert.hasText(usuario.getApellido(), "El apellido no puede ser nulo");
        Assert.hasText(usuario.getSexo(), "El Genero no puede ser vacio");
        Assert.notNull(usuario.getFechaNacimiento(), "La fecha de nacimiento no puede ser vacia");
        Assert.hasText(usuario.getUsuario(), "El usuario no puede ser nulo");
        Assert.hasText(usuario.getCorreo(), "El correo no puede ser nulo");
        Assert.hasText(usuario.getImagen(), "Debe agregar una imagen");

        validarUsuario(usuario.getUsuario());
        validarCorreo(usuario.getCorreo());
        validarRol(usuario.getIdRol().getId());
        validarEstado(usuario.getIdEstado().getId());

        // verifica si la contraseña es nula
        String passwordNew = usuario.getPassword() == null
                ? PasswordUtils.hash(usuario.getNombre() + usuario.getFechaNacimiento() + usuario.getSexo())
                : usuario.getPassword();

        if (passwordNew.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        usuario.setPassword(PasswordUtils.hash(passwordNew)); // aqui password

        if(usuario.getFechaCreacion() == null){
            usuario.setFechaCreacion(LocalDateTime.now());
        }

        emailServices.sendEmail(usuario.getCorreo(), "Bienvenido a DeepMarket", "Gracias por registrarte en DeepMarket. tu Usuarios es:" + usuario.getUsuario() + " Tu contraseña es: " + passwordNew);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuario, Integer id) {
        // Validaciones de parámetros
        Assert.notNull(id, "El id no puede ser nulo");
        Assert.notNull(usuario, "El usuario no puede ser nulo");
        Assert.hasText(usuario.getUsuario(),"el usuario no puede ser vacio");
        Assert.hasText(usuario.getNombre(), "El nombre no puede ser nulo");
        Assert.hasText(usuario.getApellido(), "El apellido no puede ser nulo");
        Assert.hasText(usuario.getSexo(), "El Genero no puede ser vacio");
        Assert.notNull(usuario.getFechaNacimiento(), "La fecha de nacimiento no puede ser vacia");
        Assert.hasText(usuario.getCorreo(), "El correo no puede ser nulo");
        Assert.notNull(usuario.getCorreo(), "El correo no puede ser nulo");
        //Assert.hasText(usuario.getPassword(), "La contraseña no puede ser nulo");

        // Validaciones adicionales
        validarRol(usuario.getIdRol().getId());
        validarEstado(usuario.getIdEstado().getId());

        // Buscar el usuario existente
        Usuario usuarioActual = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El usuario con id " + id + " no existe"));


        // Verificar si el username ya existe en otro usuario
        if (usuarioRepository.existsByUsuarioAndIdNot(usuario.getUsuario(), id)) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso por otro usuario");
        }

        // Verificar si el correo ya existe en otro usuario
        if (usuarioRepository.existsByCorreoAndIdNot(usuario.getCorreo(), id)) {
            throw new IllegalArgumentException("El correo ya está en uso por otro usuario");
        }

        // Actualizar solo los campos permitidos (evitar sobrescribir campos sensibles)
        usuarioActual.setNombre(usuario.getNombre());
        usuarioActual.setApellido(usuario.getApellido());
        usuarioActual.setSexo(usuario.getSexo());
        usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioActual.setCorreo(usuario.getCorreo());
        usuarioActual.setIdRol(usuario.getIdRol());
        usuarioActual.setIdEstado(usuario.getIdEstado());

        // Actualizar fecha de modificación
        usuarioActual.setFechaCreacion(LocalDateTime.now());

        return usuarioRepository.save(usuarioActual);
    }

    public Usuario RecuperarCorreo(String correo){
        Assert.hasText(correo, "El correo no puede ser nulo");
        Assert.notNull(correo, "El correo no puede estar vacio");

        if (!usuarioRepository.findByCorreo(correo).isPresent()){
            throw new IllegalArgumentException("El correo " + correo + " no existe en DeepMarket");
        }

        // buscar usuario por correo
        Usuario usuario = usuarioRepository.findByCorreo(correo).get();
        String nombre = usuario.getNombre();
        String sexo = usuario.getSexo();
        String fecha = usuario.getFechaNacimiento().toString();
        String password = nombre + fecha + sexo;

        usuario.setPassword(PasswordUtils.hash(password));

        emailServices.sendEmail(correo, "Contraseña cambiada con exito", "Tu contraseña temporal es:" + password + " actualiza tu contraseña lo mas pronto posible en DeepMarket" );
        return usuarioRepository.save(usuario);
    }

    // autenticar usuario
    public Usuario autenticarUsuario(String usuario, String contrasena) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);
        if(usuarioOptional.isPresent() && PasswordUtils.matches(contrasena, usuarioOptional.get().getPassword()) && usuarioOptional.get().getIdEstado().getId() == 1){
            return usuarioOptional.get();
        }
        return null;
    }

}
