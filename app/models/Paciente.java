package models;

import com.avaje.ebean.Model;
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
    private Long id;

    /**
     * Nombre del Paciente.
     */
    public String nombre;

    /**
     * Apellido del Paciente.
     */
    public String apellido;


}
