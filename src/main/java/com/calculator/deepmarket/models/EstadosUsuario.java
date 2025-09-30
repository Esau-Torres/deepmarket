package com.calculator.deepmarket.models;

import jakarta.persistence.*;

@Entity
@Table(name = "estados_usuario", schema = "public")
public class EstadosUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

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

    public EstadosUsuario() {}

    public EstadosUsuario(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadosUsuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}