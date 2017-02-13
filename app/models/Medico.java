package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by am.espinosa11 on 13/02/2017.
 */

@Entity
public class Medico extends Model
{
    /**
     * id del Medico.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del Medico.
     */
    private String nombre;

    /**
     * Apellido del Medico.
     */
    private String apellido;

    public Medico(String pNombre, String pApellido)
    {
        this.nombre=pNombre;
        this.apellido= pApellido;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
