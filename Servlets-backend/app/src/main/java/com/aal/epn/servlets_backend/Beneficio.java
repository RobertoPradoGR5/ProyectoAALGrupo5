package com.aal.epn.servlets_backend;

/**
 * Created by RobertoPrado on 08/08/2016.
 */
public class Beneficio {
    private String nombre;
    private String categoria;
    private String descripcion;
    private String urlImagen;

    public Beneficio() {
    }

    public Beneficio(String nombre, String categoria, String descripcion, String urlImagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
