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
public class MedicoEspecialista extends Model
{
    /**
     * id del Medico Especialista.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del Medico Especialista.
     */
    private String nombre;

    /**
     * Apellido del Medico Especialista.
     */
    private String apellido;


    /**
     * Especialidad del Medico Especialista.
     */
    private String especialidad;

    public MedicoEspecialista(String pNombre,String pApellido, String pEspecialidad)
    {
        this.nombre = pNombre;
        this.apellido = pApellido;
        this.especialidad= pEspecialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
