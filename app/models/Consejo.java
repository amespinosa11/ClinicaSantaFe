package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by am.espinosa11 on 15/02/2017.
 */
@Entity
@Table(name = "consejo")
public class Consejo extends Model
{
    /**
     * id del Consejo.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String asunto;

    private String descripcion;

    @ManyToOne @JsonBackReference @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne  @JsonBackReference @JoinColumn(name = "paciente_id")

    private Paciente paciente;


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

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
