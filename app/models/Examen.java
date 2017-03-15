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
@Table(name = "examen")
public class Examen extends Model {

    /**
     * id del examen.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String resultados;

    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date fecha;

    @ManyToOne @JsonBackReference
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Examen(String pTipo, Date pFecha,String pResultados )
    {
        this.tipo = pTipo;
        this.resultados = pResultados;
        this.fecha = pFecha;
    }


    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getResultados() {
        return resultados;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
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

    public static Find<Long,Examen> find = new Find<Long,Examen>(){};

    public Paciente getPaciente() {
        return paciente;
    }
}
