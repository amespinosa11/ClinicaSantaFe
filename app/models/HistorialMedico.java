package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
/**
 * Created by fa.lopez10 on 13/02/2017.
 */
@Entity
public class HistorialMedico extends Model
{
    /**
     * id del historial medico
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public HistorialMedico()
    {

    }
}
