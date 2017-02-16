package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jp.otalora10 on 15/02/2017.
 */
@Entity
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