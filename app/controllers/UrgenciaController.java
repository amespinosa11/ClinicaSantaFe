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
 * Created by fa.lopez10 on 14/02/2017.
 */
public class UrgenciaController extends Controller
{
    public Result create()
    {
        Urgencia urgencia = Form.form(Urgencia.class).bindFromRequest().get();
        urgencia.save();
        return ok(toJson(urgencia));
    }

    public Result read() {

        List<Urgencia> urgencias = new Model.Finder(String.class, Urgencia.class).all();
        return ok(toJson(urgencias));
    }

    public Result delete(Long pId)
    {
        Urgencia urgencia = Urgencia.find.byId(pId);
        urgencia.delete();
        return ok(toJson(urgencia));
    }

    public Result update(Long pId)
    {
        Urgencia pUrgencia = Form.form(Urgencia.class).bindFromRequest().get();
        Urgencia urgencia = Urgencia.find.byId(pId);

        urgencia.setDescripcion(pUrgencia.getDescripcion());
        urgencia.setFecha(pUrgencia.getFecha());
        urgencia.save();

        return ok(toJson(urgencia));
    }

    public Result getById(Long pId)
    {
        Urgencia urgencia = Urgencia.find.byId(pId);
        return ok(toJson(urgencia));
    }
}
