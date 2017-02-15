package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;



@Entity
public class Registro extends Model
{
    /**
     * id del Medico.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha de Expedicion.
     */
    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date fechaExpedicion;

    /**
     * La frecuencia cardiaca del paciente.
     */
    private Long frecuenciaCardiaca;

    private Long presionCardiaca;

    private String nivelEstres;

    private String nivelActividadFisica;

    public Registro(Long pFrecuencia, Date pFecha, Long pPresionCardiaca, String pNivel, String pNivelA)
    {
        this.frecuenciaCardiaca=pFrecuencia;
        this.fechaExpedicion= pFecha;
        this.presionCardiaca = pPresionCardiaca;
        this.nivelEstres = pNivel;
        this.nivelActividadFisica = pNivelA;
    }

    public Registro(String pNivel, String pNivelA)
    {
        this.nivelEstres = pNivel;
        this.nivelActividadFisica = pNivelA;
    }

    public Registro(Long pFrecuencia, Long pPresionCardiaca, String pNivel, String pNivelA)
    {
        this.frecuenciaCardiaca=pFrecuencia;
        this.presionCardiaca = pPresionCardiaca;
        this.nivelEstres = pNivel;
        this.nivelActividadFisica = pNivelA;
    }


    public Long getId() {
        return id;
    }

    public Long getFrecuencia() {
        return frecuenciaCardiaca;
    }

    public Date getFecha() {
        return fechaExpedicion;
    }

    public Long getPresionCardiaca(){
        return presionCardiaca;
    }

    public String getNivelEstres(){
        return nivelEstres;
    }

    public String getNivelActividadFisica(){
        return  nivelActividadFisica;
    }


    public void setFrecuenciaCardiaca(Long pFrecuencia) {
        this.frecuenciaCardiaca = pFrecuencia;
    }

    public void setFechaExpedicion(Date pFecha) {
        this.fechaExpedicion = pFecha;
    }

    public void setNivelActividadFisica(String nivelActividadFisica) {
        this.nivelActividadFisica = nivelActividadFisica;
    }

    public void setNivelEstres(String nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    public void setPresionCardiaca(Long presionCardiaca) {
        this.presionCardiaca = presionCardiaca;
    }

    public static Find<Long,Registro> find = new Find<Long,Registro>(){};

    public static Registro bind(JsonNode j) {

        Long frecuenciaCardiaca = j.findPath("frecuenciaCardiaca").asLong();
        String nivelEstres = j.findPath("nivelEstres").asText();
        String nivelActividad = j.findPath("nivelActividad").asText();
        Long presionCardiaca = j.findPath("presionCardiaca").asLong();


        /** Registro p = new Registro(nivelEstres,nivelActividad);
         *
         */
        Registro p = new Registro(frecuenciaCardiaca, presionCardiaca, nivelEstres, nivelActividad);
        return p;
    }



}
