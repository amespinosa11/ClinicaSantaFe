package controllers;

import com.avaje.ebean.Model;
import models.Consejo;
import models.Medico;
import models.Paciente;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by am.espinosa11 on 15/02/2017.
 */
public class ConsejoController extends Controller
{

    public Result create(Long idMedico,Long idPaciente)
    {
        Consejo consejo = Form.form(Consejo.class).bindFromRequest().get();
        Medico m = Medico.find.byId(idMedico);
        Paciente p = Paciente.find.byId(idPaciente);

        consejo.setMedico(m);
        consejo.setPaciente(p);
        m.setConsejo(consejo);
        p.setConsejo(consejo);
        m.save();
        p.save();

        consejo.save();
        return ok(toJson(consejo));
    }

    public Result read(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        List<Consejo>consejos = p.getConsejos();
        return ok(toJson(consejos));
    }

    public Result delete(Long pId)
    {
        Consejo consejo = Consejo.find.byId(pId);
        consejo.delete();
        return ok(toJson(consejo));
    }

    public Result update(Long pId) {
        Consejo consejo = Form.form(Consejo.class).bindFromRequest().get();
        Consejo pS = Consejo.find.byId(pId);

        pS.setAsunto(consejo.getAsunto());
        pS.setDescripcion(consejo.getDescripcion());

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Consejo consejo = Consejo.find.byId(pId);
        return ok(toJson(consejo));
    }

    }
