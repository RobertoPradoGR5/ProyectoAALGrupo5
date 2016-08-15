package com.aal.epn.servlets_backend;

import android.text.method.DateTimeKeyListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by RobertoPrado on 07/08/2016.
 */
public class Fecha {
    Date fecha;

    public Fecha() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void Today(){
        this.fecha=new Date();
    }
    public String getDia(){
        String resultado="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        resultado=sdf.format(this.fecha);
        return resultado;
    }
    public String getDia(Date date){
        String resultado="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        resultado=sdf.format(date);
        return resultado;
    }
}
