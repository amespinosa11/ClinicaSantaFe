package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.ArrayList;

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

    private Integer edad;

    private Double peso;

    private Double estatura;

    private String sexo;

    private ArrayList<Registro> registros;

    public Paciente(String pNombre, String pApellido,Integer pEdad, Double pPeso, Double pEstatura,String pSexo)
    {
        this.nombre = pNombre;
        this.apellido = pApellido;
        this.edad = pEdad;
        this.peso = pPeso;
        this.estatura = pEstatura;
        this.sexo = pSexo;
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

    public Double getEstatura() {
        return estatura;
    }

    public Double getPeso() {
        return peso;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public static Find<Long,Paciente> find = new Find<Long,Paciente>(){};

    public Integer frecuenciaCardiacaMaxima()
    {
        Integer max = 0;
        Integer nPeso = peso.intValue();
        if(sexo.equalsIgnoreCase("mujer"))
        {
            max = ((210 - ((1/2)*edad))-((1/100)*nPeso));
        }
        else
        {
            max = ((210 - ((1/2)*edad))-((1/100)*nPeso))+4;
        }
        return max;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void agregarRegistro(Registro pRegistro)
    {
        registros.add(pRegistro);
    }

    public void inicializarRegistros(Registro pRegistro)
    {
        registros = new ArrayList<>();
        registros.add(pRegistro);
    }
}
