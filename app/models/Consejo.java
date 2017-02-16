package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by am.espinosa11 on 15/02/2017.
 */
@Entity
public class Consejo extends Model
{
    /**
     * id del Paciente.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String asunto;

    private String descripcion;


    public Consejo(String pAsunto, String pDescripcion)
    {
        this.asunto = pAsunto;
        this.descripcion = pDescripcion;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Find<Long,Consejo> find = new Find<Long,Consejo>(){};
}
