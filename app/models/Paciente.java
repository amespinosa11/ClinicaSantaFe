package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by am.espinosa11 on 12/02/2017.
 */

@Entity
public class Paciente extends Model
{

    /**
     * id del Paciente.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Historial de mediciones del paciente. Se compone de registros
     */
    private ArrayList<Registro> registros = new ArrayList<Registro>();
    /**
     * Nombre del Paciente.
     */
    private String nombre;

    /**
     * Apellido del Paciente.
     */
    private String apellido;

    public Paciente(String pNombre, String pApellido)
    {
        this.nombre = pNombre;
        this.apellido = pApellido;
    }

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarRegistro(Registro pRegistro)
    {
        registros.add(pRegistro);
    }

    public ArrayList<Registro> darRegistros() { return registros; }

    /**
     * Para dos fechas, responde con el arreglo de registros que están dentro de este rango
     * @param fecha1 fecha inicial del rango
     * @param fecha2 fecha final del rango
     * @return
     */
    public ArrayList<Registro> darRegistrosEnRangoFechas(Date fecha1, Date fecha2)
    {
        ArrayList<Registro> registrosEnRango = new ArrayList<Registro>();
        for (Registro registro : registros )
            if(registro.getFecha().after(fecha1) && registro.getFecha().before(fecha2))
                registrosEnRango.add(registro);
        return registrosEnRango;
    }

    public static Find<Long,Paciente> find = new Find<Long,Paciente>(){};

    public static Paciente bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        String apellido = j.findPath("apellido").asText();

        Paciente p = new Paciente(nombre,apellido);
        return p;
    }

}
