package com.calculator.deepmarket.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "sexo", nullable = false, length = Integer.MAX_VALUE)
    private String sexo;

    @Column(name = "f_nacimiento", nullable = false)
    private LocalDate fNacimiento;

    @Column(name = "f_creacion", nullable = false)
    private LocalDate fCreacion;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;

    @Column(name = "pass", nullable = false)
    private String pass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    public Usuario() {

    }
    public Usuario(Integer id, String nombre, String apellido, Integer edad, String sexo, LocalDate fNacimiento,
            LocalDate fCreacion, String correo, String usuario, String pass, Rol rol, Estado estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.fNacimiento = fNacimiento;
        this.fCreacion = fCreacion;
        this.correo = correo;
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
        this.estado = estado;
    }

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFNacimiento() {
        return fNacimiento;
    }

    public void setFNacimiento(LocalDate fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public LocalDate getFCreacion() {
        return fCreacion;
    }

    public void setFCreacion(LocalDate fCreacion) {
        this.fCreacion = fCreacion;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", fNacimiento=" + fNacimiento +
                ", fCreacion=" + fCreacion +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", pass='" + pass + '\'' +
                ", rol=" + rol +
                ", estado=" + estado +
                '}';
    }
}