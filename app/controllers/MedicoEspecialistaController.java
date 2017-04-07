package controllers;

import com.avaje.ebean.Model;
import models.MedicoEspecialista;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.loginMedicoEspecialista;

import java.util.List;

import static play.libs.Json.toJson;
import views.html.loginMedico;
import views.html.loginPaciente;
import views.html.loginMedicoEspecialista;



/**
 * Created by am.espinosa11 on 14/02/2017.
 */
public class MedicoEspecialistaController extends Controller
{
    public Result login()
    {
        Form f = Form.form(LoginFormDataMedicoEspecialista.class);
        return ok(loginMedicoEspecialista.render());
    }

    public Result authenticate()
    {
        Form<LoginFormDataMedicoEspecialista> loginForm = Form.form(LoginFormDataMedicoEspecialista.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(loginMedicoEspecialista.render());
        } else {
            session().clear();
            session("emailMedEsp", loginForm.get().emailMedEsp);
            return redirect(
                    routes.MedicoEspecialistaController.read()
            );
        }
    }

    public Result create()
    {
        MedicoEspecialista medico = Form.form(MedicoEspecialista.class).bindFromRequest().get();
        medico.save();
        return ok(toJson(medico));
    }

    @Security.Authenticated(SecuredMedicoEspecialista.class)

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
