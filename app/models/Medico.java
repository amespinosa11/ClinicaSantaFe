package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by am.espinosa11 on 13/02/2017.
 */

@Entity
@Table(name = "medico")
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

    @OneToMany(mappedBy = "medico") @JsonBackReference
    private List<Consejo> consejos;

    @OneToMany(mappedBy = "medico")
    private List<Notificacion>notificaciones;

    @ManyToMany
    private List<Paciente> pacientes;


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

    public static Find<Long,Medico> find = new Find<Long,Medico>(){};

    public void setConsejo(Consejo consejo)
    {
        this.consejos.add(consejo);
    }

    public void setPaciente(Paciente paciente)
    {
        this.pacientes.add(paciente);
    }

    public void setNotificacion(Notificacion notificaciones) {
        this.notificaciones.add(notificaciones);
    }
}
