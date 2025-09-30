package com.calculator.deepmarket.repositories;

import com.calculator.deepmarket.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.calculator.deepmarket.models.Rol;
import com.calculator.deepmarket.models.EstadosUsuario;
import java.util.List;
import java.time.LocalDateTime;

import java.util.Optional;

@Repository // se pone la anotacion de repositorio
//se pone publica para poder usarla
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Optional <Usuario> findById(Integer Id);
    // List<Usuario> findAll();
    // Page<Usuario> findAll(Pageable pageable);
    // Usuario save(Usuario user);
    // void delete(Usuario user);
    // void deleteById(Integer Id);
    // long count();

    // buscar por email
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findById(Integer id);
    Boolean existsByUsuarioAndIdNot(String usuario, Integer id);
    Boolean existsByCorreoAndIdNot(String correo, Integer id);
    // buscar usuario por nombre ignorando las mayusculas y minnusculas ignore case
    //List<Usuario> findByNombreContainingIgnoreCase(String nombre); // containig busca un contenido

    // buscar usuario por rol.
    //List<Usuario> findByIdEstado(EstadosUsuario idEstado);

    //List<Usuario> findByIdRolAndIdEstado(Rol idRol,EstadosUsuario idEstado);

    //buscar usuarios registrados entre fechas

    //List<Usuario> findByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    //Buscar usuarios cuyo nombre empieza con un sufijo
    //List<Usuario> findByNombreStartingWith(String prefijo);
    //Buscar usuarios cuyo nombre termine con un sufijo
    //List<Usuario> findByApellidoEndingWith(String prefijo);
    //buscar un usuario registrado a partir de una fecha
    //List<Usuario> findByfechaCreacionAfter(LocalDateTime fecha);
    // buscar usuario por nombre del rol
    //List<Usuario> findByRolnombre(String nombre);

    //List<Usuario> findAllByIdEstado(EstadosUsuario idEstado);

    // con jpql
    //@Query("SELECT u FROM Usuario u WHERE u.idRol.nombre = :nombreRol")
    //List<Usuario> findByidRolnombre(@Param("nombreRol") String rol);

    // buscar usuario por nombre de rol con sql antivo
    //@Query(nativeQuery = true,value = "SELECT * FROM usuarios u INNER JOIN roles r ON u.id_rol = r.id_rol WHERE r.nombre = :idRol")
    //List<Usuario> findByidRolnombreSQL(@Param("idRol") String nombre);

    // llama a una funcion sql
    //@Query(nativeQuery = true,value = "SELECT * FROM obtener_usuarios_activos()")
    //List<Usuario> obtener_usuarios_activos();

    //@Procedure(name = "eliminar_usuario_inactivos")
    //void eliminar_usuario_inactivos();

    // NOTA: repositorio es la capa de acceso a datos del CRUD

    List<Usuario> findEstadoByNombre(String name);
}
