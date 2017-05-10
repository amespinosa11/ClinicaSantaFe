package controllers;

import com.avaje.ebean.Model;
import models.Tratamiento;
import models.Paciente;
import models.Tratamiento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by fa.lopez10 on 13/03/2017.
 */
public class TratamientoController extends Controller {
    public Result create(Long idPaciente)
    {
        Tratamiento tratamiento = Form.form(Tratamiento.class).bindFromRequest().get();
        Paciente p = Paciente.find.byId(idPaciente);

        tratamiento.setPaciente(p);
        p.setTratamiento(tratamiento);
        p.save();
        tratamiento.save();

        return ok(toJson(tratamiento));
    }

//    @Security.Authenticated(Secured.class)
    public Result readId(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        List<Tratamiento> tratamientos = p.getTratamientos();
        return ok(toJson(tratamientos));
    }

    public Result read(String email) {

        /*List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();

        int i = 0;
        Long id = 1L;
        while(i<pacientes.size())
        {
            if(pacientes.get(i).getCorreo().equals(email))
            {
                id = pacientes.get(i).getId();
            }
            i++;
        }
        Paciente p = Paciente.find.byId(id);
        */
        List<Tratamiento> tratamientos = new Model.Finder(String.class, Tratamiento.class).all();
        //List<Tratamiento> tratamientos = p.getTratamientos();

        List<String> array = new ArrayList<>();

        for(int j = 0;j<tratamientos.size();j++)
        {
            String d = "";

            d = " Fecha Inicio: "+tratamientos.get(j).getFechaInicio().toString();

            d += " Descripción: "+tratamientos.get(j).getDescripcion();

            d += " Fecha Fin: "+tratamientos.get(j).getFechaFin();

            array.add(d);
        }

        array.add("Ana");
        return ok(pacientesTratamientos.render(array));
    }

    public Result delete(Long pId)
    {
        Tratamiento tratamiento = Tratamiento.find.byId(pId);
        tratamiento.delete();
        return ok(toJson(tratamiento));
    }

    public Result update(Long pId) {
        Tratamiento tratamiento = Form.form(Tratamiento.class).bindFromRequest().get();
        Tratamiento pS = Tratamiento.find.byId(pId);

        pS.setFechaFin(tratamiento.getFechaFin());
        pS.setFechaInicio(tratamiento.getFechaInicio());
        pS.setDescripcion(tratamiento.getDescripcion());

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Tratamiento tratamiento = Tratamiento.find.byId(pId);
        return ok(toJson(tratamiento));
    }
}
