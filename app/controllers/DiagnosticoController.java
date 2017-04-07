package controllers;

import com.avaje.ebean.Model;
import models.Diagnostico;
import models.Medico;
import models.Paciente;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.libs.Json.toJson;

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

    @Security.Authenticated(Secured.class)
    public Result read(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        List<Diagnostico>diagnosticos = p.getDiagnosticos();
        return ok(toJson(diagnosticos));
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
