package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fa.lopez10 on 13/03/2017.
 */
@Entity
@Table(name = "tratamiento")
public class Tratamiento extends Model {

    /**
     * id del tratamiento.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date fecha;

    @ManyToOne @JsonBackReference
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getId() {
        return id;
    }

    public static Find<Long,Tratamiento> find = new Find<Long,Tratamiento>(){};

    public Paciente getPaciente() {
        return paciente;
    }
}
