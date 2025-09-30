package com.calculator.deepmarket.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "ediciones_mensajes", schema = "public")
public class EdicionesMensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_edicion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_mensaje", nullable = false)
    private Mensaje idMensaje;

    @Column(name = "contenido_anterior", length = Integer.MAX_VALUE)
    private String contenidoAnterior;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_edicion", nullable = false)
    private Instant fechaEdicion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mensaje getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Mensaje idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getContenidoAnterior() {
        return contenidoAnterior;
    }

    public void setContenidoAnterior(String contenidoAnterior) {
        this.contenidoAnterior = contenidoAnterior;
    }

    public Instant getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Instant fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public EdicionesMensaje() {}

    public EdicionesMensaje(Integer id, Mensaje idMensaje, String contenidoAnterior, Instant fechaEdicion) {
        this.id = id;
        this.idMensaje = idMensaje;
        this.contenidoAnterior = contenidoAnterior;
        this.fechaEdicion = fechaEdicion;
    }

    @Override
    public String toString() {
        return "EdicionesMensaje{" +
                "id=" + id +
                ", idMensaje=" + idMensaje +
                ", contenidoAnterior='" + contenidoAnterior + '\'' +
                ", fechaEdicion=" + fechaEdicion +
                '}';
    }
}