package com.calculator.deepmarket.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rols")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer id_rol;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    public Rol() {
    }

    public Rol(String nombre, Integer id_rol) {
        this.nombre = nombre;
        this.id_rol = id_rol;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "rol{" +
                "id_rol=" + id_rol +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
