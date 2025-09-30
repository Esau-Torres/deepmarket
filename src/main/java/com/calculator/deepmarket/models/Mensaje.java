package com.calculator.deepmarket.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "mensajes", schema = "public")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_chat", nullable = false)
    private Chat idChat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Column(name = "contenido", length = Integer.MAX_VALUE)
    private String contenido;

    @Column(name = "tipo_mensaje", nullable = false, length = 20)
    private String tipoMensaje;

    @Column(name = "url_archivo")
    private String urlArchivo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_envio", nullable = false)
    private Instant fechaEnvio;

    @Column(name = "fecha_edicion")
    private Instant fechaEdicion;

    @ColumnDefault("false")
    @Column(name = "eliminado")
    private Boolean eliminado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Chat getIdChat() {
        return idChat;
    }

    public void setIdChat(Chat idChat) {
        this.idChat = idChat;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public Instant getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Instant fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Instant getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Instant fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Mensaje(){}

    public Mensaje(Integer id, Chat idChat, Usuario idUsuario, String contenido, String tipoMensaje, String urlArchivo, Instant fechaEnvio, Instant fechaEdicion, Boolean eliminado) {
        this.id = id;
        this.idChat = idChat;
        this.idUsuario = idUsuario;
        this.contenido = contenido;
        this.tipoMensaje = tipoMensaje;
        this.urlArchivo = urlArchivo;
        this.fechaEnvio = fechaEnvio;
        this.fechaEdicion = fechaEdicion;
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", idChat=" + idChat +
                ", idUsuario=" + idUsuario +
                ", contenido='" + contenido + '\'' +
                ", tipoMensaje='" + tipoMensaje + '\'' +
                ", urlArchivo='" + urlArchivo + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", fechaEdicion=" + fechaEdicion +
                ", eliminado=" + eliminado +
                '}';
    }
}