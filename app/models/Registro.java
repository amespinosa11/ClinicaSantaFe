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
    private Integer frecuenciaCardiaca;

    private Integer presionSanguinea1;

    private Integer presionSanguinea2;

    private Integer nivelEstres;

    private Integer nivelActividadFisica;

    private Paciente paciente;

    public Registro(Date pDia,Integer p1,Integer p2, Integer p3,Integer p4,Integer p5)
    {
        this.fechaExpedicion = pDia;
        this.presionSanguinea1 = p1;
        this.presionSanguinea2 = p2;
        this.frecuenciaCardiaca = p3;
        this.nivelEstres = p4;
        this.nivelActividadFisica = p5;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public Integer getPresionSanguinea1() {
        return presionSanguinea1;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public Integer getNivelActividadFisica() {
        return nivelActividadFisica;
    }

    public Integer getNivelEstres() {
        return nivelEstres;
    }

    public Integer getPresionSanguinea2() {
        return presionSanguinea2;
    }

    public void setPresionSanguinea1(Integer presionSanguinea1) {
        this.presionSanguinea1 = presionSanguinea1;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public void setNivelActividadFisica(Integer nivelActividadFisica) {
        this.nivelActividadFisica = nivelActividadFisica;
    }

    public void setNivelEstres(Integer nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    public void setPresionSanguinea2(Integer presionSanguinea2) {
        this.presionSanguinea2 = presionSanguinea2;
    }

    public void setPaciente(Long pId) {
        this.paciente = Paciente.find.byId(pId);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Long getId() {
        return id;
    }

    public String getColor(Long pId)
    {
        Paciente paciente = Paciente.find.byId(pId);
        String color = "SIN";
        if(this.frecuenciaCardiaca>=paciente.frecuenciaCardiacaMaxima()||
                (this.presionSanguinea1>=135 &&this.presionSanguinea2>=85) || this.nivelEstres>=8)
        {
            color = "ROJO";
        }
        else if(nivelEstres>=6 &&(this.frecuenciaCardiaca<paciente.frecuenciaCardiacaMaxima() &&(
        this.presionSanguinea1<135 && this.presionSanguinea2<85)))
        {
            color = "AMARILLO";
        }
        else
        {
            color = "VERDE";
        }

        return color;

    }



    public static Find<Long,Registro> find = new Find<Long,Registro>(){};
}
