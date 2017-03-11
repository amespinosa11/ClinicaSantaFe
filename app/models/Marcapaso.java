package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by jp.otalora10 on 15/02/2017.
 */
@Entity
@Table(name = "marcapaso")
public class Marcapaso extends Model
{
    /**
     * id del Marcapaso.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    /**
     * frecuencia en la que funciona el marcapasos
     */

    private Double frecuenciaMarcapaso;

    @OneToOne
    private Paciente paciente;


    public Marcapaso(Double pFrecuencia)
    {
        this.frecuenciaMarcapaso = pFrecuencia;
    }

    public void setFrecuenciaMarcapasos(Double pFrecuencia) {
        this.frecuenciaMarcapaso = pFrecuencia;
    }


    public Long getId() {
        return id;
    }

    public Double getFrecuencia() {
        return frecuenciaMarcapaso;
    }


    public static Find<Long,Marcapaso> find = new Find<Long,Marcapaso>(){};
}
