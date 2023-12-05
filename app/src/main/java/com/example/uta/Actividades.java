package com.example.uta;

public class Actividades {

    private String tipoActv;
    private String activity;
    private String fecha;
    private String personaRegistar;
    private String personaSolicita;
    private String estado;
    private String horas;
    private String observaciones;


    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setTipoActv(String tipoActv) {
        this.tipoActv = tipoActv;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPersonaSolicita(String personaSolicita) {
        this.personaSolicita = personaSolicita;
    }

    public void setPersonaRegistar(String personaRegistar) {
        this.personaRegistar = personaRegistar;
    }

    public String getPersonaSolicita() {
        return personaSolicita;
    }

    public String getEstado() {
        return estado;
    }

    public String getPersonaRegistar() {
        return personaRegistar;
    }

    public String getHoras() {
        return horas;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getTipoActv() {
        return tipoActv;
    }

    public String getActivity() {
        return activity;
    }


    public Actividades(String tipoActv, String activity, String fecha, String personaRegistar, String personaSolicita, String estado, String horas, String observaciones) {
        this.tipoActv = tipoActv;
        this.activity = activity;
        this.fecha = fecha;
        this.personaRegistar = personaRegistar;
        this.personaSolicita = personaSolicita;
        this.estado = estado;
        this.horas = horas;
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "**ACTIVIDADES**\n" +
                "TIPO ACTIVIDAD: "+tipoActv+"\n" +
                "ACTICIDAD: " + activity +"\n"+
                "FECHA: " + fecha + "\n" +
                "PERSONA REGISTRA: " + personaRegistar + "\n" +
                "PERSONA SOLICITA: " + personaSolicita + "\n" +
                "ESTADO: " + estado + "\n" +
                "HORAS: " + horas + "\n" +
                "OBSERVACIONES: " + observaciones + '\'';
    }
}
