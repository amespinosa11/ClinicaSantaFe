package controllers;

import com.avaje.ebean.Model;
import models.Diagnostico;
import models.Examen;
import models.Paciente;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;
import views.html.*;
/**
 * Created by fa.lopez10 on 13/03/2017.
 */
public class ExamenController extends Controller {
    public Result create(Long idPaciente)
    {
        Examen examen = Form.form(Examen.class).bindFromRequest().get();
        Paciente p = Paciente.find.byId(idPaciente);

        examen.setPaciente(p);
        p.setExamen(examen);
        p.save();
        examen.save();

        return ok(toJson(examen));
    }

//    @Security.Authenticated(Secured.class)
    public Result readId(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        List<Examen> examenes = p.getExamenes();
        return ok(toJson(examenes));
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
        List<Examen> examenes = p.getExamenes();
        */
        List<Examen> examenes = new Model.Finder(String.class, Examen.class).all();
        //List<Tratamiento> tratamientos = p.getTratamientos();

        List<String> array = new ArrayList<>();

        for(int j = 0;j<examenes.size();j++)
        {
            String s = "Tipo: "+examenes.get(j).getTipo();
            s+= " Resultados: "+examenes.get(j).getResultados();
            s+= " Fecha: " + examenes.get(j).getFecha();

            array.add(s);
        }
        //List<Diagnostico>diagnosticos = p.getDiagnosticos();

        return ok(pacientesExamenes.render(array));
    }

    public Result delete(Long pId)
    {
        Examen examen = Examen.find.byId(pId);
        examen.delete();
        return ok(toJson(examen));
    }

    public Result update(Long pId) {
        Examen examen = Form.form(Examen.class).bindFromRequest().get();
        Examen pS = Examen.find.byId(pId);

        pS.setFecha(examen.getFecha());
        pS.setResultados(examen.getResultados());
        pS.setTipo(examen.getTipo());


        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Examen examen = Examen.find.byId(pId);
        return ok(toJson(examen));
    }
}
