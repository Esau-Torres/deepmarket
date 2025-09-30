package com.calculator.deepmarket.models;

import jakarta.persistence.*;

@Entity
@Table(name = "estados_post", schema = "public")
public class EstadosPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_post", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
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

    public EstadosPost() {}

    public EstadosPost(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadosPost{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}