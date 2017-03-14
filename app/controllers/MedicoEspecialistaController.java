package controllers;

import com.avaje.ebean.Model;
import models.MedicoEspecialista;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by am.espinosa11 on 14/02/2017.
 */
public class MedicoEspecialistaController extends Controller
{
    public Result create()
    {
        MedicoEspecialista medico = Form.form(MedicoEspecialista.class).bindFromRequest().get();
        medico.save();
        return ok(toJson(medico));
    }

    public Result read() {

        List<MedicoEspecialista> medicos = new Model.Finder(String.class, MedicoEspecialista.class).all();
        return ok(toJson(medicos));
    }

    public Result delete(Long pId)
    {
        MedicoEspecialista medico = MedicoEspecialista.find.byId(pId);
        medico.delete();
        return ok(toJson(medico));
    }

    public Result update(Long pId)
    {
        MedicoEspecialista medico = Form.form(MedicoEspecialista.class).bindFromRequest().get();
        MedicoEspecialista pS = MedicoEspecialista.find.byId(pId);

        pS.setApellido(medico.getApellido());
        pS.setNombre(medico.getNombre());
        pS.setEspecialidad(medico.getEspecialidad());
        pS.save();

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        MedicoEspecialista medico = MedicoEspecialista.find.byId(pId);
        return ok(toJson(medico));
    }

    //loquesea
}
