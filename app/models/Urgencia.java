package models;

import java.util.Date;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.JsonNode;
import play.data.format.*;
import javax.persistence.*;

/**
 * Created by fa.lopez10 on 13/02/2017.
 */
@Entity
@Table(name = "urgencia")
public class Urgencia extends Model
{
    /**
     * id de la urgencia
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha en que se genera la urgencia
     */
    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date fecha;


    /**
     * Descripcion del caso de la urgencia
     */
    private String descripcion;

    @ManyToOne@JsonBackReference
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Urgencia(String pDescripcion)
    {
        this.fecha = new Date();
        this.descripcion = pDescripcion;
    }

    public Urgencia(Date pFecha, String pDescripcion)
    {
        this.fecha =  pFecha==null ? new Date() : pFecha;
        this.descripcion = pDescripcion;
    }

    public Long getId(){ return id;}

    public Date getFecha()
    {
        return this.fecha;
    }

    public String getDescripcion()
    {
        return this.descripcion;
    }

    public void setFecha(Date pFecha)
    {
        this.fecha = pFecha;
    }

    public void setDescripcion(String pDescripcion)
    {
        this.descripcion = pDescripcion;
    }

    public static Find<Long,Urgencia> find = new Find<Long,Urgencia>(){};

    public void setPaciente(Long pId) {
        this.paciente = Paciente.find.byId(pId);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public static Urgencia bind(JsonNode j) {
    String descripcion = j.findPath("descripcion").asText();

    Urgencia p = new Urgencia(descripcion);
    return p;
}

}
