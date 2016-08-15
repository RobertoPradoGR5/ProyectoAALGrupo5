package com.aal.epn.servlets_backend;

/**
 * Created by RobertoPrado on 09/08/2016.
 */
public class Almuerzo {
    private String pricipal;
    private String secundario;
    private String bebida;
    private String postre;
    private Float precio;
    private String tipo;
    private String urlImagen;

    public Almuerzo() {
    }

    public String getPricipal() {
        return pricipal;
    }

    public void setPricipal(String pricipal) {
        this.pricipal = pricipal;
    }

    public String getSecundario() {
        return secundario;
    }

    public void setSecundario(String secundario) {
        this.secundario = secundario;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
