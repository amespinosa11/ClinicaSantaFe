package controllers;

import com.avaje.ebean.Model;
import models.Consejo;
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

    public Result create()
    {
        Consejo consejo = Form.form(Consejo.class).bindFromRequest().get();
        consejo.save();
        return ok(toJson(consejo));
    }

    public Result read() {

        List<Consejo> consejos = new Model.Finder(String.class, Consejo.class).all();
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
