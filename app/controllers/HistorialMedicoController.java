package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
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
public class HistorialMedicoController extends Controller{
    public Result create()
    {
        HistorialMedico historial = Form.form(HistorialMedico.class).bindFromRequest().get();
        historial.save();
        return ok(toJson(historial));
    }

    public Result read() {

        List<HistorialMedico> historiales = new Model.Finder(String.class, HistorialMedico.class).all();
        return ok(toJson(historiales));
    }

    public Result delete(Long pId)
    {
        HistorialMedico historial = HistorialMedico.find.byId(pId);
        historial.delete();
        return ok(toJson(historial));
    }

    public Result update(Long pId)
    {
        HistorialMedico historial = Form.form(HistorialMedico.class).bindFromRequest().get();
        HistorialMedico hViejo = HistorialMedico.find.byId(pId);

        hViejo.setDescripcion(historial.getDescripcion());
        hViejo.save();

        return ok(toJson(hViejo));
    }

    public Result getById(Long pId)
    {
        HistorialMedico historial = HistorialMedico.find.byId(pId);
        return ok(toJson(historial));
    }


}
