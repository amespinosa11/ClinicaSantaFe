package controllers;

import com.avaje.ebean.Model;
import models.Medico;
import models.Registro;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.loginMedico;
import views.html.loginPaciente;
import views.html.medicosRegistrados;
import views.html.vistaGeneralMedico;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by am.espinosa11 on 14/02/2017.
 */
public class MedicoController extends Controller
{
    public Result pagPrincipal()
    {
        return ok(vistaGeneralMedico.render(""));
    }

    public Result login()
    {
        Form f = Form.form(LoginFormDataMedico.class);
        return ok(loginMedico.render());
    }

    public Result registro()
    {
        Form f = Form.form(Medico.class);
        return ok(medicosRegistrarse.render());
    }

    public Result authenticate() {
        Form<LoginFormDataMedico> loginForm = Form.form(LoginFormDataMedico.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(loginMedico.render());
        } else {
            session().clear();
            session("emailMed", loginForm.get().emailMed);
            return ok(medicosRegistrados.render(loginForm.get().emailMed));
        }
    }

    public Result registrados(String email)
    {
        return ok(medicosRegistrados.render("email"));
    }

    public Result create()
    {
        Form<FormRegistroMedico> formDataPacienteForm = Form.form(FormRegistroMedico.class).bindFromRequest();

        Medico medico = new Medico(formDataPacienteForm.get().nombreMedico,formDataPacienteForm.get().apellidoMedico);
        medico.setCorreo(formDataPacienteForm.get().correoMedico);
        medico.setContraseña(formDataPacienteForm.get().contraseñaMedico);
        medico.save();
        return ok(medicosRegistrados.render(medico.getCorreo()));
    }

    @Security.Authenticated(SecuredMedico.class)
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

    public Result darNotificaciones(String email)
    {
        List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        //List<Tratamiento> tratamientos = p.getTratamientos();

        List<String> array = new ArrayList<>();

        for(int j = 0;j<registros.size();j++)
        {
            String d = "Frecuencia cardiaca: " + registros.get(j).getFrecuenciaCardiaca().toString() + "\n";
            d += " Actividad fisica: "+ registros.get(j).getNivelActividadFisica() + "\n";
            d += " Nivel Estres: "+ registros.get(j).getNivelEstres()+ "\n";
            d += " Presión sanguinea: " + registros.get(j).getPresionSanguinea1()+" - "+registros.get(j).getPresionSanguinea2()+ "\n";
            d += " Fecha: " + registros.get(j).getFechaExpedicion();
            d += " Color: " + registros.get(j).getColor(1L);

            array.add(d);
        }

        //List<Registro>registros = p.getRegistros();

        // return ok(toJson(registros));
        return ok(Notificaciones.render(array));
    }

    @Security.Authenticated(SecuredMedico.class)
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
