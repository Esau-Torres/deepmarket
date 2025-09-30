package com.calculator.deepmarket.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts", schema = "public")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "imagen", length = Integer.MAX_VALUE)
    private String imagen;

    @Column(name = "imagen2", length = Integer.MAX_VALUE)
    private String imagen2;

    @Column(name = "imagen3", length = Integer.MAX_VALUE)
    private String imagen3;

    @Column(name = "imagen4", length = Integer.MAX_VALUE)
    private String imagen4;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais")
    private Pais idPais;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ciudad")
    private Ciudad idCiudad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria idCategoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_post")
    private EstadosPost idEstadoPost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen4() {
        return imagen4;
    }

    public void setImagen4(String imagen4) {
        this.imagen4 = imagen4;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public EstadosPost getIdEstadoPost() {
        return idEstadoPost;
    }

    public void setIdEstadoPost(EstadosPost idEstadoPost) {
        this.idEstadoPost = idEstadoPost;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Post(){}

    public Post(Integer id, String titulo, String descripcion, BigDecimal precio, String imagen, String imagen2, String imagen3, String imagen4, LocalDateTime fechaCreacion, Pais idPais, Ciudad idCiudad, Categoria idCategoria, EstadosPost idEstadoPost, Usuario idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.fechaCreacion = fechaCreacion;
        this.idPais = idPais;
        this.idCiudad = idCiudad;
        this.idCategoria = idCategoria;
        this.idEstadoPost = idEstadoPost;
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                ", imagen2='" + imagen2 + '\'' +
                ", imagen3='" + imagen3 + '\'' +
                ", imagen4='" + imagen4 + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", idPais=" + idPais +
                ", idCiudad=" + idCiudad +
                ", idCategoria=" + idCategoria +
                ", idEstadoPost=" + idEstadoPost +
                ", idUsuario=" + idUsuario +
                '}';
    }
}