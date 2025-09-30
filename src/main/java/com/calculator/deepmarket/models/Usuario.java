package com.calculator.deepmarket.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "imagen", length = Integer.MAX_VALUE)
    private String imagen;

    @Column(name = "sexo", length = Integer.MAX_VALUE)
    private String sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol idRol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    private EstadosUsuario idEstado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagen(){return imagen;}

    public void setImagen(String imagen){this.imagen = imagen;}

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public EstadosUsuario getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosUsuario idEstado) {
        this.idEstado = idEstado;
    }

    public Usuario() {}

    public Usuario(Integer id, EstadosUsuario idEstado, Rol idRol, String password, String usuario, String correo, LocalDateTime fechaCreacion, String sexo, LocalDate fechaNacimiento, String imagen,String apellido, String nombre) {
        this.id = id;
        this.idEstado = idEstado;
        this.idRol = idRol;
        this.password = password;
        this.usuario = usuario;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.imagen = imagen;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", imagen='" + imagen + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaCreacion=" + fechaCreacion +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", idRol=" + idRol +
                ", idEstado=" + idEstado +
                '}';
    }
}