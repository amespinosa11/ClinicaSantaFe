package controllers;

import com.avaje.ebean.Model;
import models.Diagnostico;
import models.Medico;
import models.Paciente;
import models.Tratamiento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;
import views.html.*;
/**
 * Created by am.espinosa11 on 15/02/2017.
 */
public class DiagnosticoController extends Controller
{

    public Result create(Long idPaciente)
    {
        Diagnostico diagnostico = Form.form(Diagnostico.class).bindFromRequest().get();
        Paciente p = Paciente.find.byId(idPaciente);

        diagnostico.setPaciente(p);
        p.setDiagnostico(diagnostico);
        p.save();

        diagnostico.save();
        return ok(toJson(diagnostico));
    }

//    @Security.Authenticated(Secured.class)
    public Result readId(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        List<Diagnostico>diagnosticos = p.getDiagnosticos();
        return ok(toJson(diagnosticos));
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
        List<Diagnostico> diagnosticos = new Model.Finder(String.class, Diagnostico.class).all();
        //List<Tratamiento> tratamientos = p.getTratamientos();

        List<String> array = new ArrayList<>();

        for(int j = 0;j<diagnosticos.size();j++)
        {
            String d = "Fecha: " + diagnosticos.get(j).getFecha();

            d+= "Descripción: " + diagnosticos.get(j).getDescripcion();

            array.add(d);
        }
        //List<Diagnostico>diagnosticos = p.getDiagnosticos();

        return ok(pacientesDiadnosticos.render(array));
    }

    public Result delete(Long pId)
    {
        Diagnostico diagnostico = Diagnostico.find.byId(pId);
        diagnostico.delete();
        return ok(toJson(diagnostico));
    }

    public Result update(Long pId) {
        Diagnostico diagnostico = Form.form(Diagnostico.class).bindFromRequest().get();
        Diagnostico pS = Diagnostico.find.byId(pId);

        pS.setDescripcion(diagnostico.getDescripcion());
        pS.setFechaExpedicion(diagnostico.getFecha());

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Diagnostico dia = Diagnostico.find.byId(pId);
        return ok(toJson(dia));
    }
    //loque sea

}
