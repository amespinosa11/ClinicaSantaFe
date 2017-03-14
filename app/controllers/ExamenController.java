package controllers;

import models.Examen;
import models.Paciente;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

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

    public Result read(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        List<Examen> examenes = p.getExamenes();
        return ok(toJson(examenes));
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
        pS.setDescripcion(examen.getDescripcion());

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Examen examen = Examen.find.byId(pId);
        return ok(toJson(examen));
    }
}
