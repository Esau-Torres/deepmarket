package com.calculator.deepmarket.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "chats", schema = "public")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario1_id", nullable = false)
    private Usuario usuario1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario2_id", nullable = false)
    private Usuario usuario2;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ultima_actividad", nullable = false)
    private Instant ultimaActividad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getUltimaActividad() {
        return ultimaActividad;
    }

    public void setUltimaActividad(Instant ultimaActividad) {
        this.ultimaActividad = ultimaActividad;
    }

    public Chat(){}

    public Chat(Integer id, Usuario usuario1, Usuario usuario2, Instant fechaCreacion, Instant ultimaActividad) {
        this.id = id;
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActividad = ultimaActividad;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", usuario1=" + usuario1 +
                ", usuario2=" + usuario2 +
                ", fechaCreacion=" + fechaCreacion +
                ", ultimaActividad=" + ultimaActividad +
                '}';
    }
}