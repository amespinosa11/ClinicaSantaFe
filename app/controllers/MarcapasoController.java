package controllers;

import com.avaje.ebean.Model;
import models.Marcapaso;
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
public class MarcapasoController extends Controller
{


    public Result create(Long idPaciente)
    {
        Marcapaso marca = Form.form(Marcapaso.class).bindFromRequest().get();
        Paciente p = Paciente.find.byId(idPaciente);

        marca.setPaciente(p);
        p.iniciarMarcapaso();
        p.save();

        marca.save();
        return ok(toJson(marca));
    }

    public Result read(Long idPaciente) {

        Paciente p = Paciente.find.byId(idPaciente);
        Marcapaso marca = p.getMarcapaso();
        return ok(toJson(marca));
    }

    public Result delete(Long pId)
    {
        Marcapaso marcapaso = Marcapaso.find.byId(pId);
        marcapaso.delete();
        return ok(toJson(marcapaso));
    }

    @Security.Authenticated(SecuredMedicoEspecialista.class)
    public Result update(Long pId) {
        Marcapaso marca = Form.form(Marcapaso.class).bindFromRequest().get();
        Marcapaso pS = Marcapaso.find.byId(pId);

        pS.setFrecuenciaMarcapasos(marca.getFrecuencia());

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Marcapaso dia = Marcapaso.find.byId(pId);
        return ok(toJson(dia));
    }



}
