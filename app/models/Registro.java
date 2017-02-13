package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;



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
    private Date fechaExpedicion;

    /**
     * La frecuencia cardiaca del paciente.
     */
    private Long frecuenciaCardiaca;

    public Registro(Long pFrecuencia, Date pFecha)
    {
        this.frecuenciaCardiaca=pFrecuencia;
        this.fechaExpedicion= pFecha;
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

    public void setFrecuenciaCardiaca(Long pFrecuencia) {
        this.frecuenciaCardiaca = pFrecuencia;
    }

    public void setFechaExpedicion(Date pFecha) {
        this.fechaExpedicion = pFecha;
    }


}
