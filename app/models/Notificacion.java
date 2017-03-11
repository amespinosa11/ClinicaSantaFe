package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.JsonNode;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * Created by am.espinosa11 on 11/03/2017.
 */
@Entity
@Table(name = "notificacion")
public class Notificacion extends Model
{
    /**
     * id de la notificacion.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String descripcion;

    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date fecha;



    @ManyToOne @JsonBackReference
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToOne @JsonBackReference
    private Registro registro;

    public Notificacion(String pTipo, String descripcion)
    {
        this.tipo = pTipo;
        this.descripcion = descripcion;

    }


    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getId() {
        return id;
    }



    public Paciente getPaciente() {
        return paciente;
    }

    public Registro getRegistro() {
        return registro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }
}
