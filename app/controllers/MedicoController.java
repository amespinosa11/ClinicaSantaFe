package controllers;

import com.avaje.ebean.Model;
import models.Medico;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.loginMedico;
import views.html.loginPaciente;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by am.espinosa11 on 14/02/2017.
 */
public class MedicoController extends Controller
{
    public Result login()
    {
        Form f = Form.form(LoginFormDataMedico.class);
        return ok(loginMedico.render());
    }

    public Result authenticate() {
        Form<LoginFormDataMedico> loginForm = Form.form(LoginFormDataMedico.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(loginMedico.render());
        } else {
            session().clear();
            session("emailMed", loginForm.get().emailMed);
            return redirect(
                    routes.MedicoController.read()
            );
        }
    }

    public Result create()
    {
        Medico medico = Form.form(Medico.class).bindFromRequest().get();
        medico.save();
        return ok(toJson(medico));
    }

    public Result read() {

        List<Medico> medicos = new Model.Finder(String.class, Medico.class).all();
        return ok(toJson(medicos));
    }

    public Result delete(Long pId)
    {
        Medico medico = Medico.find.byId(pId);
        medico.delete();
        return ok(toJson(medico));
    }

    public Result update(Long pId)
    {
        Medico medico = Form.form(Medico.class).bindFromRequest().get();
        Medico pS = Medico.find.byId(pId);

        pS.setApellido(medico.getApellido());
        pS.setNombre(medico.getNombre());
        pS.setCorreo(medico.getCorreo());
        pS.setContraseña(medico.getContraseña());
        pS.save();

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Medico medico = Medico.find.byId(pId);
        return ok(toJson(medico));
    }
}
