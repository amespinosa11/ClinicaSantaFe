package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
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

    public static Paciente bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        String apellido = j.findPath("apellido").asText();

        Paciente p = new Paciente(nombre,apellido);
        return p;
    }

}
