package controllers;

import com.avaje.ebean.Model;
import models.Marcapaso;
import models.Paciente;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by am.espinosa11 on 15/02/2017.
 */
public class MarcapasoController extends Controller
{

    public Result create()
    {
        Marcapaso marcapaso = Form.form(Marcapaso.class).bindFromRequest().get();
        marcapaso.save();
        return ok(toJson(marcapaso));
    }

    public Result read() {

        List<Marcapaso> marcapaso = new Model.Finder(String.class, Marcapaso.class).all();
        return ok(toJson(marcapaso));
    }

    public Result delete(Long pId)
    {
        Marcapaso marcapaso = Marcapaso.find.byId(pId);
        marcapaso.delete();
        return ok(toJson(marcapaso));
    }

    public Result update(Long pId) {
        Marcapaso marcapaso = Form.form(Marcapaso.class).bindFromRequest().get();
        Marcapaso pS = Marcapaso.find.byId(pId);

        pS.setFrecuenciaMarcapasos(marcapaso.getFrecuencia());

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Marcapaso marcapaso = Marcapaso.find.byId(pId);
        return ok(toJson(marcapaso));
    }

}
