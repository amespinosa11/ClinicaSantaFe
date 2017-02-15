package controllers;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Registro;
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
 * Created by otalora on 12/02/2017.
 */
public class RegistroController extends Controller
{

    public Result create()
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();
        registro.save();

        System.out.println(registro);
        return ok(toJson(registro));
    }

    public Result read() {

        List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        return ok(toJson(registros));
    }

    public Result delete(Long pId)
    {
        Registro registro = Registro.find.byId(pId);
        registro.delete();
        return ok(toJson(registro));
    }

    public Result update(Long pId)
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();
        Registro pS = Registro.find.byId(pId);

        pS.setFrecuenciaCardiaca(registro.getFrecuencia());
        pS.setFechaExpedicion(registro.getFecha());
        pS.setNivelActividadFisica(registro.getNivelActividadFisica());
        pS.setNivelEstres(registro.getNivelEstres());
        pS.setPresionCardiaca(registro.getPresionCardiaca());
        pS.save();

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Registro registro = Registro.find.byId(pId);
        return ok(toJson(registro));
    }

}
