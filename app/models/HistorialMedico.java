package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
/**
 * Created by fa.lopez10 on 13/02/2017.
 */
@Entity
public class HistorialMedico extends Model
{
    /**
     * id del historial medico
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Descripcion breve de las condiciones del paciente
     */
    private String descripcion;

    public HistorialMedico(String pDescripcion)
    {
        this.descripcion = pDescripcion;
    }

    public Long getId(){ return id;}

    public String getDescripcion()
    {
        return this.descripcion;
    }

    public void setDescripcion(String pDescripcion)
    {
        this.descripcion = pDescripcion;
    }


    public static Find<Long,HistorialMedico> find = new Find<Long,HistorialMedico>(){};
}
