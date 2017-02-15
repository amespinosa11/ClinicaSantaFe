package controllers;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Paciente;
import play.data.Form;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import static play.libs.Json.toJson;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
/**
 * Created by am.espinosa11 on 12/02/2017.
 */
public class PacienteController extends Controller
{

    public Result create()
    {
        Paciente paciente = Form.form(Paciente.class).bindFromRequest().get();
        paciente.save();
        return ok(toJson(paciente));
    }

    public Result read() {

        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        return ok(toJson(pacientes));
    }

    public Result delete(Long pId)
    {
        Paciente paciente = Paciente.find.byId(pId);
            paciente.delete();
        return ok(toJson(paciente));
    }

    public Result update(Long pId)
    {
        Paciente paciente = Form.form(Paciente.class).bindFromRequest().get();
        Paciente pS = Paciente.find.byId(pId);

        pS.setApellido(paciente.getApellido());
        pS.setNombre(paciente.getNombre());
        pS.save();

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Paciente paciente = Paciente.find.byId(pId);
        return ok(toJson(paciente));
    }

}
