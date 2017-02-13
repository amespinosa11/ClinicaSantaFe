package models;

import java.util.Date;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

/**
 * Created by fa.lopez10 on 13/02/2017.
 */
@Entity
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
    private Date fecha;

    /**
     * Descripcion del caso de la urgencia
     */
    private String descripcion;

    public Urgencia(Date pFecha, String pDescripcion)
    {
        this.fecha = pFecha;
        this.descripcion = pDescripcion;
    }

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
}
