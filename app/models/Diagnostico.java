package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "diagnostico")
public class Diagnostico extends Model
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
    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date fechaExpedicion;

    /**
     * Descripcion del diagnostico.
     */
    private String descripcion;

    @ManyToOne
    private Paciente paciente;

    public Diagnostico(String pDescripcion, Date pFecha)
    {
        this.descripcion=pDescripcion;
        this.fechaExpedicion= pFecha;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fechaExpedicion;
    }

    public void setDescripcion(String pDescripcion) {
        this.descripcion = pDescripcion;
    }

    public void setFechaExpedicion(Date pFecha) {
        this.fechaExpedicion = pFecha;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public static Find<Long,Diagnostico> find = new Find<Long,Diagnostico>(){};


}
