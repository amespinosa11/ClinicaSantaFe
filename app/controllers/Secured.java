package controllers;

import models.Paciente;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;
import controllers.LoginFormDataPaciente;

/**
 * Created by am.espinosa11 on 05/04/2017.
 */
public class Secured extends Security.Authenticator
{

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("emailPac");
    }

    @Override
    public Result onUnauthorized(Context ctx)
    {
        return redirect(routes.PacienteController.login());
    }


}
